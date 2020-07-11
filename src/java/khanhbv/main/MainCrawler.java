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
import khanhbv.crawler.DMGRCategoryCrawler;
import khanhbv.crawler.DMGRProductDetailCrawler;
import khanhbv.crawler.DMGRProductLinkByCategoryCrawler;
import khanhbv.crawler.DMRCategoryCrawler;
import khanhbv.crawler.DMRProductDetailCrawler;
import khanhbv.crawler.DMRProductLinkByCategoryCrawler;
import khanhbv.utils.Helper;
import khanhbv.utils.StringConstant;

/**
 *
 * @author buivankhanh
 */
public class MainCrawler {

    public static void main(String[] args) {
        Map<String, String> categoryMap;
//        DienMayLinhBrandCrawler brandCrawler = new DienMayLinhBrandCrawler(null);
//        brandCrawler.getCategories("https://dienmaylinh.vn/");
//        DienMayLinhCategoriesCrawler categoriesCrawler = new DienMayLinhCategoriesCrawler(null);
//        categoriesCrawler.getCategories("https://dienmaylinh.vn/");
//          DienMayLinhProductLinkByCategoryCrawler productLinkCrawler = new DienMayLinhProductLinkByCategoryCrawler(null, "https://dienmaylinh.vn/dien-gia-dung", "TIVI");
//          productLinkCrawler.getProductLink();
//        DMLProductDetailCrawler detailCrawler = new DMLProductDetailCrawler(null, "TIVI", "https://dienmaylinh.vn/may-lanh-casper-1-5hp-ec-12tl22");
//        detailCrawler.getProductDetail();
//        DMGRCategoryCrawler categoryCrawler = new DMGRCategoryCrawler(null);
//        categoryCrawler.getCategories("https://dienmaygiare.net/");
//        DMGRProductLinkByCategoryCrawler productCrawler = new DMGRProductLinkByCategoryCrawler(null, "https://dienmaygiare.net/dieu-hoa/", "TIVI");
//        productCrawler.getProductLink();
//        DMGRProductDetailCrawler detailCrawler = new DMGRProductDetailCrawler(null, "TIVI", "https://dienmaygiare.net/quat-dieu-hoa-daikiosan-dka-04000b/");
//        detailCrawler.getProductDetail();
//        DMRCategoryCrawler categoryCrawler = new DMRCategoryCrawler(null);
//        categoryMap = categoryCrawler.getCategories("https://dienmayre.vn/");
//        for (Map.Entry<String, String> entry : categoryMap.entrySet()) {
//            System.out.println(entry.getKey() + "   "  +entry.getValue());
//            DMRProductLinkByCategoryCrawler productCrawler = new DMRProductLinkByCategoryCrawler(null, entry.getKey(), entry.getValue());
//            productCrawler.getProductLink();
//        }


//            DMRProductDetailCrawler detailCrawler = new DMRProductDetailCrawler(null, "TIVI", "https://dienmayre.vn/smart-tivi-sharp-50-inch-2t-c50ae1x.html");
//            detailCrawler.getProductDetail();
//        String data = Helper.findPowerNumberInSring("Điện năng tiêu thụ: 23.750hp");
//        System.out.println("data: " + data);
//        float result = Helper.converPower("Công suất lạnh 25000BTU (2.5HP)", "25000");
//        System.out.println("result: " + result);
    }

}
