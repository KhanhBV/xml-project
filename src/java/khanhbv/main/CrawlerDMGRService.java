/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import khanhbv.crawler.DMGRCategoryCrawler;
import khanhbv.crawler.DMGRProductDetailCrawler;
import khanhbv.crawler.DMGRProductLinkByCategoryCrawler;
import khanhbv.dlo.BrandBLO;
import khanhbv.dlo.CategoryBLO;
import khanhbv.dlo.ProductBLO;
import khanhbv.dto.ProductTestDTO;
import khanhbv.entities.Brand;
import khanhbv.entities.Category;
import khanhbv.entities.Product;
import khanhbv.utils.Helper;
import khanhbv.utils.StringConstant;

/**
 *
 * @author vankhanhbui
 */
public class CrawlerDMGRService {

    private Map<String, String> categoryDMGRMap;
    private List<ProductTestDTO> listProductTemDMGR;
    private List<ProductTestDTO> listProductByCategory;
    private BrandBLO brandBLO = new BrandBLO();
    private List<Category> categoryDB;
    private List<Brand> brandDB;
    
    public void crawlDMGR() {
        crawlCategory();
    }

    public void crawlCategory() {
        CategoryBLO categoryBLO = new CategoryBLO();
        DMGRCategoryCrawler categoryCrawler = new DMGRCategoryCrawler(null);
        categoryDMGRMap = categoryCrawler.getCategories(StringConstant.DMGR_URL_DOMAIN_NAME);
        System.out.println("-------category va link produt cua Dien May Gia Re-------");
        if (categoryDMGRMap != null) {
            for (Map.Entry<String, String> entry : categoryDMGRMap.entrySet()) {
                if (entry.getKey().contains(StringConstant.DMGR_FAN_STRING.toUpperCase())) {
                    categoryBLO.insertCategory(entry.getKey().trim().toUpperCase());
                }

            }
            categoryDB = categoryBLO.getAllCategory();
            for (Map.Entry<String, String> entry : categoryDMGRMap.entrySet()) {
                System.out.println(entry.getKey() + "  " + entry.getValue());
                
                crawlProductByCategory(entry.getKey(), entry.getValue());
            }
        }
    }
    
    public void crawlProductByCategory(String category, String urlCategory) {
        DMGRProductLinkByCategoryCrawler crawlerLinkByCategoryCrawler = new DMGRProductLinkByCategoryCrawler(null, urlCategory,category);
        List<String> linkProductLinkDMGR = crawlerLinkByCategoryCrawler.getProductLink();
        listProductTemDMGR = new ArrayList<>();
        if (linkProductLinkDMGR != null) {
            System.out.println(category + linkProductLinkDMGR.size() + "San Pham: ");
            for (int i = 0; i < linkProductLinkDMGR.size(); i++) {
                crawlProductDetailByLink(category, linkProductLinkDMGR.get(i));
                
            }
        }
        if (listProductByCategory == null) {
            listProductByCategory = new ArrayList<>();
        }
        
        Helper.addListToList(listProductTemDMGR, listProductByCategory);
        
    }

    public void crawlProductDetailByLink(String categoryStr, String urlProduct) {
        DMGRProductDetailCrawler productDetailCrawler = new DMGRProductDetailCrawler(null, categoryStr, urlProduct);
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
            if (dto.getLinkProduct() != null) {
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
            productBLO.insertProduct(product);
        }
    }

    public Map<String, String> getCategoryDMGRMap() {
        return categoryDMGRMap;
    }

    public void setCategoryDMGRMap(Map<String, String> categoryDMGRMap) {
        this.categoryDMGRMap = categoryDMGRMap;
    }

    public List<ProductTestDTO> getListProductTemDMGR() {
        return listProductTemDMGR;
    }

    public void setListProductTemDMGR(List<ProductTestDTO> listProductTemDMGR) {
        this.listProductTemDMGR = listProductTemDMGR;
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
