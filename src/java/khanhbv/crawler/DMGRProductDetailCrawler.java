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
import khanhbv.utils.XmlSyntaxChecker;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author vankhanhbui
 */
public class DMGRProductDetailCrawler extends BaseCrawler {

    private String url;
    private String category;
    private static String beginSyntax = "<div class=\"woocommerce-notices-wrapper\"></div>";
    private static String endSyntax = "<button class=\"action\" data-dialog-close>Đóng</button>";

    public DMGRProductDetailCrawler(ServletContext context, String category, String url) {
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
            exp = "//tr[position() != last()]/td[not (img) and "
                    + "not (@style) and not (p) and (contains(text(),'"
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
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V8
                    + "') )]";

            String powerStr = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
            
            if (powerStr.isEmpty()) {
                exp = "//div[@class='thongsokythuat']//td[contains(text(),'"
                        + StringConstant.POWER_STRING_V15
                        + "')]";
                powerStr = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
            }
//            int index = powerStr.indexOf(":");
            
//            powerStr = Helper.formatPower(powerStr);
//            if (index != -1) {
//                String subString = powerStr.substring(0, index + 1);
//                powerStr = powerStr.replaceAll(subString, "").trim();
//            }
            

            
            System.out.println(powerStr);
//            if (!powerStr.isEmpty()) {
//                String powerBefortConvert = Helper.findPowerNumberInSring(powerStr);
//                float power = Helper.converPower(powerStr, powerBefortConvert);
//                System.out.println(name);
//                
//                System.out.println(power);
//                System.out.println(powerStr);
//            }

            exp = "//div[@class='zoom']/img[last()]";
            Node imgNode = (Node) xPath.evaluate(exp, doc, XPathConstants.NODE);
            String imgUrl = "";
            if (imgNode != null) {
                imgUrl = imgNode.getAttributes().getNamedItem("src").getNodeValue();
//                System.out.println(imgUrl.trim());
            }
            System.out.println("----------------------------");
           
            dto.setCategoryID(category.trim());
            dto.setImageURL(imgUrl.trim());
            dto.setName(name.trim());
            dto.setUrl(url);
            dto.setPower(powerStr);

            return dto;
        }

        return null;
    }
}
