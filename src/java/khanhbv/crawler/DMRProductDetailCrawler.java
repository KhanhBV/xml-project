/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.crawler;

import java.io.BufferedReader;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import khanhbv.dto.ProductTestDTO;
import khanhbv.utils.Helper;
import khanhbv.utils.StringConstant;
import khanhbv.utils.XMLHelper;
import khanhbv.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author vankhanhbui
 */
public class DMRProductDetailCrawler extends BaseCrawler {

    private String url;
    private String category;
    private static String beginSyntax = "<div class=\"columns_lefts col-xs-12 col-sm-12 col-md-12 col-lg-12\">";
    private static String endSyntax = "div class=\"thumb_list col-xs-12 col-sm-12 col-md-12 col-lg-12\">";

    public DMRProductDetailCrawler(ServletContext context, String category, String url) {
        super(context);
        this.category = category;
        this.url = url;
    }

    public ProductTestDTO getProductDetail() {
        BufferedReader reader = null;
        ProductTestDTO dto = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);
//                XMLHelper.writeTestFileDocument(document);
                dto = domParserProductDetails(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;

    }

    public ProductTestDTO domParserProductDetails(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);

        ProductTestDTO dto = null;

        if (doc != null) {
            dto = new ProductTestDTO();
            XPath xPath = XMLUtils.creatXPath();
            String exp = "//h1";
            String name = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

//            exp = "//tr[position() != last()]/td[not (img) and not (@style) and not (p) "
//                    + "and contains(text(),'"
//                    + StringConstant.POWER_STRING_V1
//                    + "')]";
            exp = "//div[@class='thong_so_ky_thuat_chi_tiet']//ul/li[not (strong) and "
                    + "(contains(text(),'"
                    + StringConstant.POWER_STRING_V1
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V2
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V3
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V4
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V5
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V6
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V7
//                    + "') or contains(text(),'"
//                    + StringConstant.POWER_STRING_V17
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V18
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V8
                    
                    + "')"
                    + "or contains(text(),'"
                    + StringConstant.POWER_STRING_V11
                    + "'))]";

            String powerStr = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
            if (powerStr.isEmpty()) {
                exp = "//div[@class='thong_so_ky_thuat_chi_tiet']//ul/li[span[contains(text(),'"
                        + StringConstant.POWER_STRING_V1
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V2
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V3
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V4
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V5
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V6
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V8
                        + "')"
                        + "or contains(text(),'"
                        + StringConstant.POWER_STRING_V11
                        + "')]]/span[last()]";
                powerStr = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

                if (powerStr.isEmpty()) {
                    exp = "//div[@class='thong_so_ky_thuat_chi_tiet']//tr[td/p[not(span) and "
                            + "(contains(text(),'"
                            + StringConstant.POWER_STRING_V12
                            + "') or contains(text(),'"
                            + StringConstant.POWER_STRING_V13
                            + "') or contains(text(),'"
                            + StringConstant.POWER_STRING_V14
                            + "'))]]/td[last()]";
                    powerStr = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

                    if (powerStr.isEmpty()) {
                        exp = "//div[@class='thong_so_ky_thuat_chi_tiet']//ul/li[contains(text(),'"
                                + StringConstant.POWER_STRING_V16
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V12
                                + "')]";
                        powerStr = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

                    }
                }
            }
//         

            exp = "//div/div/li[@class=\"images-sub col-lg-12 col-md-12 col-sm-12 col-xs-12\"]/a/img";
            Node imgNode = (Node) xPath.evaluate(exp, doc, XPathConstants.NODE);
            String imgUrl = "";
            if (imgNode != null) {
                imgUrl = imgNode.getAttributes().getNamedItem("src").getNodeValue();
                System.out.println(imgUrl.trim());
            }
            System.out.println("----------------------------");

            
            dto.setImageURL(imgUrl.trim());
            dto.setName(name.trim());
            dto.setLinkProduct(url);
            dto.setPower(powerStr);

            return dto;
        }

        return null;
    }
}
