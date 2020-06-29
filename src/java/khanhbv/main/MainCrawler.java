/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.main;

import java.util.Map;
import khanhbv.crawler.DMLProductDetailCrawler;
import khanhbv.crawler.DienMayLinhBrandCrawler;
import khanhbv.crawler.DienMayLinhCategoriesCrawler;
import khanhbv.crawler.DienMayLinhProductLinkByCategoryCrawler;
import khanhbv.utils.StringConstant;

/**
 *
 * @author buivankhanh
 */
public class MainCrawler {

    public static void main(String[] args) {


//        DienMayLinhBrandCrawler brandCrawler = new DienMayLinhBrandCrawler(null);
//        brandCrawler.getCategories("https://dienmaylinh.vn/");

//        DienMayLinhCategoriesCrawler categoriesCrawler = new DienMayLinhCategoriesCrawler(null);
//        categoriesCrawler.getCategories("https://dienmaylinh.vn/");

          DienMayLinhProductLinkByCategoryCrawler productLinkCrawler = new DienMayLinhProductLinkByCategoryCrawler(null, "https://dienmaylinh.vn/-tivi", "TIVI");
          productLinkCrawler.getProductLink();

//        DMLProductDetailCrawler detailCrawler = new DMLProductDetailCrawler(null, "TIVI", "https://dienmaylinh.vn/quat-dieu-hoa-sanaky-snk-4500s");
//        detailCrawler.getProductDetail();



    }
}
