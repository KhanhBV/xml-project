/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.main;

import java.util.List;
import java.util.Map;
import khanhbv.crawler.DienMayLinhBrandCrawler;
import khanhbv.dto.ProductTestDTO;
import khanhbv.utils.StringConstant;

/**
 *
 * @author vankhanhbui
 */
public class CrawlerDMLService {
    private List<String> brandDML;
    private Map<String, String> categoryDMLMap;
    private List<ProductTestDTO> listProductTempDML;
    private List<ProductTestDTO> listProductByCategoryDML;
    
    
    
    public void crawBrandDML() {
        
        DienMayLinhBrandCrawler brandCrawler = new DienMayLinhBrandCrawler(null);
        brandDML = brandCrawler.getCategories(StringConstant.DIENMAYLINH_URL_DOMAIN_NAME);
        System.out.println("Brand: ");
        for (int i = 0; i < brandDML.size(); i++) {
            System.out.println(brandDML.get(i) + ", ");
            
        }
        
    }
}
