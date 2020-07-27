/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import khanhbv.crawler.DMGRProductLinkByCategoryCrawler;
import khanhbv.crawler.QMCategoryCrawler;
import khanhbv.crawler.QMProductDetailCrawler;
import khanhbv.crawler.QMProductLinkByCategoryCrawler;
import khanhbv.dlo.BrandBLO;
import khanhbv.dlo.CategoryBLO;
import khanhbv.dlo.ProductBLO;
import khanhbv.dto.ProductTestDTO;
import khanhbv.entities.Brand;
import khanhbv.entities.Category;
import khanhbv.entities.Product;
import khanhbv.utils.Helper;
import khanhbv.utils.JAXBUtils;
import khanhbv.utils.StringConstant;

/**
 *
 * @author vankhanhbui
 */
public class CrawlerQLMService {
     private Map<String, String> categoryQMMap;
    private List<ProductTestDTO> listProductTemQM;
    private List<ProductTestDTO> listProductByCategory;
    private BrandBLO brandBLO = new BrandBLO();
    private List<Category> categoryDB;
    private List<Brand> brandDB;
    
  
    
    public void crawlCategory() {
        CategoryBLO categoryBLO = new CategoryBLO();
        QMCategoryCrawler categoryCrawler = new QMCategoryCrawler(null);
        categoryQMMap = categoryCrawler.getCategories(StringConstant.QM_URL_DOMAIN_NAME);
        System.out.println("-------category va link produt cua Dien May Gia Re-------");
        if (categoryQMMap != null) {
            for (Map.Entry<String, String> entry : categoryQMMap.entrySet()) {
                if (entry.getKey().contains(StringConstant.QM_GENERATOR_STRING.toUpperCase())) {
                    categoryBLO.insertCategory(entry.getKey().trim().toUpperCase());
                }

            }
            categoryDB = categoryBLO.getAllCategory();
            for (Map.Entry<String, String> entry : categoryQMMap.entrySet()) {
                System.out.println(entry.getKey() + "  " + entry.getValue());

                crawlProductByCategory(entry.getKey(), entry.getValue());
            }
        }
    }
    
    public void crawlProductByCategory(String category, String urlCategory) {
        QMProductLinkByCategoryCrawler crawlerLinkByCategoryCrawler = new QMProductLinkByCategoryCrawler(null, urlCategory, category);
        List<String> linkProductLinkQM = crawlerLinkByCategoryCrawler.getProductLink();
        listProductTemQM = new ArrayList<>();
        if (linkProductLinkQM != null) {
            System.out.println(category + linkProductLinkQM.size() + "San Pham: ");
            for (int i = 0; i < linkProductLinkQM.size(); i++) {
                crawlProductDetailByLink(category, linkProductLinkQM.get(i));

            }
        }
        if (listProductByCategory == null) {
            listProductByCategory = new ArrayList<>();
        }

        Helper.addListToList(listProductTemQM, listProductByCategory);

    }
    
    public void crawlProductDetailByLink(String categoryStr, String urlProduct) {
        QMProductDetailCrawler productDetailCrawler = new QMProductDetailCrawler(null, categoryStr, urlProduct);
        ProductTestDTO dto = productDetailCrawler.getProductDetail();
        ProductBLO productBLO = new ProductBLO();
        float power = 0;
        if (dto != null) {
            Product product = new Product();
            product.setName(dto.getName());
            brandDB = brandBLO.getAllBrand();
            Brand brand = productBLO.checkBrandOfProduct(dto.getName().trim(), brandDB);
            product.setBrandID(brand);

            Category category = productBLO.checkCtegoryOfProduct(categoryStr, categoryDB);
            product.setCategoryID(category);

            if (dto.getImageURL() != null) {
                product.setImageURL(dto.getImageURL());
                System.out.println("img: " + dto.getImageURL());
            }
            if (dto.getUrl()!= null) {
                product.setUrl(urlProduct);
            }
            if (dto.getName() != null) {
                product.setName(dto.getName());
                System.out.println("name: " + dto.getName());
            }
            if (dto.getPower() != null) {

                try {
                    String powerBefortConvert = Helper.findPowerNumberInSring(dto.getPower());
                    power = Helper.converPower(dto.getPower(), powerBefortConvert);
                    System.out.println("powerString: " + dto.getPower());
                    System.out.println("Power Service: " + power);
                    product.setPower(power);
                } catch (Exception e) {
                    e.printStackTrace();
                    product.setPower(0);
                }
            }
            boolean validate = JAXBUtils.validateXml(StringConstant.FILE_PATH_PRODUCT_XSD, product);
            if (validate) {
                productBLO.insertProduct(product);
            }
        }
    }

    public Map<String, String> getCategoryQMMap() {
        return categoryQMMap;
    }

    public void setCategoryQMMap(Map<String, String> categoryQMMap) {
        this.categoryQMMap = categoryQMMap;
    }

    public List<ProductTestDTO> getListProductTemQM() {
        return listProductTemQM;
    }

    public void setListProductTemQM(List<ProductTestDTO> listProductTemQM) {
        this.listProductTemQM = listProductTemQM;
    }

    public List<ProductTestDTO> getListProductByCategory() {
        return listProductByCategory;
    }

    public void setListProductByCategory(List<ProductTestDTO> listProductByCategory) {
        this.listProductByCategory = listProductByCategory;
    }

    public BrandBLO getBrandBLO() {
        return brandBLO;
    }

    public void setBrandBLO(BrandBLO brandBLO) {
        this.brandBLO = brandBLO;
    }

    public List<Category> getCategoryDB() {
        return categoryDB;
    }

    public void setCategoryDB(List<Category> categoryDB) {
        this.categoryDB = categoryDB;
    }

    public List<Brand> getBrandDB() {
        return brandDB;
    }

    public void setBrandDB(List<Brand> brandDB) {
        this.brandDB = brandDB;
    }
    
    
}
