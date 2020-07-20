/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import khanhbv.crawler.DMRCategoryCrawler;
import khanhbv.crawler.DMRProductDetailCrawler;
import khanhbv.crawler.DMRProductLinkByCategoryCrawler;
import khanhbv.crawler.DienMayLinhCategoriesCrawler;
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
public class DMRCrawlerService {

    private Map<String, String> categoryDMRMap;
    private List<ProductTestDTO> listProductTemDMR;
    private List<ProductTestDTO> listProductByCategory;
    private BrandBLO brandBLO = new BrandBLO();
    private List<Category> categoryDB;
    private List<Brand> brandDB;

    public void crawlDMR() {
        crawlCategory();

    }

    public void crawlCategory() {
        CategoryBLO categoryBLO = new CategoryBLO();
        DMRCategoryCrawler categoryDMRCrawler = new DMRCategoryCrawler(null);
        categoryDMRMap = categoryDMRCrawler.getCategories(StringConstant.DMR_URL_DOMAIN_NAME);
        System.out.println("------category va link product cua Dien May Re--------");
        if (categoryDMRMap != null) {
            for (Map.Entry<String, String> entry : categoryDMRMap.entrySet()) {
                categoryBLO.insertCategory(entry.getKey().trim().toUpperCase());
            }
            categoryDB = categoryBLO.getAllCategory();
            for (Map.Entry<String, String> entry : categoryDMRMap.entrySet()) {
                System.out.println(entry.getKey() + "  " + entry.getValue());

                crawlProductByCategory(entry.getKey(), entry.getValue());
            }
        }
    }

    public void crawlProductByCategory(String category, String urlCategory) {
        DMRProductLinkByCategoryCrawler crawlerLinkProductDMR = new DMRProductLinkByCategoryCrawler(null, category, urlCategory);
        List<String> linkProductListDMR = crawlerLinkProductDMR.getProductLink();
        listProductTemDMR = new ArrayList<>();
        if (linkProductListDMR != null) {
            System.out.println(category + linkProductListDMR.size() + "San Pham: ");
            for (int i = 0; i < linkProductListDMR.size(); i++) {
//                final int num = i;
//                
                crawlProductDetailsByLink(category, linkProductListDMR.get(i));
            }
        }
        if (listProductByCategory == null) {
            listProductByCategory = new ArrayList<>();
        }
        Helper.addListToList(listProductTemDMR, listProductByCategory);
    }

    public void crawlProductDetailsByLink(String categoryStr, String urlProduct) {
        DMRProductDetailCrawler productDetailCrawler = new DMRProductDetailCrawler(null, categoryStr, urlProduct);
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
                    System.out.println("powerString: " +  dto.getPower());
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

    public Map<String, String> getCategoryDMRMap() {
        return categoryDMRMap;
    }

    public void setCategoryDMRMap(Map<String, String> categoryDMRMap) {
        this.categoryDMRMap = categoryDMRMap;
    }

    public List<ProductTestDTO> getListProductTemDMR() {
        return listProductTemDMR;
    }

    public void setListProductTemDMR(List<ProductTestDTO> listProductTemDMR) {
        this.listProductTemDMR = listProductTemDMR;
    }

    public List<ProductTestDTO> getListProductByCategory() {
        return listProductByCategory;
    }

    public void setListProductByCategory(List<ProductTestDTO> listProductByCategory) {
        this.listProductByCategory = listProductByCategory;
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
