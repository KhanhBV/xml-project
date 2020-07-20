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
public class DMLProductDetailCrawler extends BaseCrawler {

    private String url;
    private String category;
    private static String beginSyntax = "<span class=\"icon-status-detail\">";
    private static String endSyntax = "<div class=\"box\" id=\"box-desc\">";

    public DMLProductDetailCrawler(ServletContext context, String category, String url) {
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
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax).replaceAll("&amp;nbsp;", "");
                XMLHelper.writeTestFileDocument(document);
                domParserProductDetails(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;

    }

    public ProductTestDTO domParserProductDetails(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);

        ProductTestDTO dto;

        if (doc != null) {
            dto = new ProductTestDTO();
            XPath xPath = XMLUtils.creatXPath();
            String exp = "//h1";
            String name = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
//            System.out.println("Name " + name);

            exp = "//li[span[span[span[span[text()='"
                    + StringConstant.POWER_STRING_V1
                    + "' or contains(text(),'"
                    + StringConstant.POWER_STRING_V2
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V3
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V4
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V5
                    + "') or contains(text(),'"
                    + StringConstant.POWER_STRING_V6
                    + "')]]]]]/span[last()]/span/span";
            String power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

            if (power.isEmpty()) {
                exp = "//tr[*//span[text()='"
                        + StringConstant.POWER_STRING_V1
                        + "' or contains(text(),'"
                        + StringConstant.POWER_STRING_V2
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V3
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V4
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V5
                        + "') or contains(text(),'"
                        + StringConstant.POWER_STRING_V6
                        + "')]]/td[last()]//span[@style='background-color:#ffffff']";
                power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

                if (power.isEmpty()) {
                    exp = "//tr[*//span[text()='"
                            + StringConstant.POWER_STRING_V1
                            + "' or contains(text(),'"
                            + StringConstant.POWER_STRING_V2
                            + "') or contains(text(),'"
                            + StringConstant.POWER_STRING_V3
                            + "') or contains(text(),'"
                            + StringConstant.POWER_STRING_V4
                            + "') or contains(text(),'"
                            + StringConstant.POWER_STRING_V5
                            + "') or contains(text(),'"
                            + StringConstant.POWER_STRING_V6
                            + "')]]//a";
                    power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

                    if (power.isEmpty()) {
                        exp = "//div[@class='entry']//ul[not(@class)]//li[text()='"
                                + StringConstant.POWER_STRING_V1
                                + "' or contains(text(),'"
                                + StringConstant.POWER_STRING_V2
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V3
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V4
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V5
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V6
                                + "')]";
                        power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
                        power = power.replace(StringConstant.POWER_STRING_V7, "");
                    }

                    if (power.isEmpty()) {
                        exp = "//table[thead//td[contains(text(),'"
                                + StringConstant.DML_SPECIFICATIONS_STRING
                                + "')]]//tr[td[text()='"
                                + StringConstant.POWER_STRING_V1
                                + "' or contains(text(),'"
                                + StringConstant.POWER_STRING_V2
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V3
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V4
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V5
                                + "') or contains(text(),'"
                                + StringConstant.POWER_STRING_V6
                                + "')]]//td[last()]";
                        power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);

                        if (power.isEmpty()) {
                            exp = "//div[@class='box-short']/div[@class='entry']//li[not (strong) and (contains(text(),'"
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
                                    + "'))]";
                            power = (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
                            power = power.replaceAll(StringConstant.POWER_STRING_V1, "");
                        }
                    }
                }
            }
             System.out.println(power);
            if (!power.isEmpty()) {
                String powerBefortConvert = Helper.findPowerNumberInSring(power);
                float powerResult = Helper.converPower(power, powerBefortConvert);
                System.out.println(name);
                
                System.out.println(powerResult);
                System.out.println(power);
            }
            
            exp = "//div[@class='gallery']//a";
            Node imageNode = (Node) xPath.evaluate(exp, doc, XPathConstants.NODE);
            String img = "";
            if (imageNode != null) {
                img = imageNode.getAttributes().getNamedItem("href").getNodeValue();
            }
            System.out.println("name: " + name);
            System.out.println("url:  " + img);

            System.out.println("Power: " + power);
            System.out.println("----------------");
            
            dto.setCategoryID(category);
            dto.setImageURL(img);
            dto.setName(name);
            dto.setUrl(url);
            dto.setPower(power);
            return dto;

        }

        return null;
    }

}
