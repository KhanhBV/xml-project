/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.crawler;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import khanhbv.utils.StringConstant;
import khanhbv.utils.XMLHelper;
import khanhbv.utils.XMLUtils;
import khanhbv.utils.XmlSyntaxChecker;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author vankhanhbui
 */
public class DMGRCategoryCrawler extends BaseCrawler {

    private static String beginSyntax = "<div class=\"menu-danh-muc-san-pham-container\">";
    private static String endSyntax = "<div class=\"col-md-9 no-padding main-navigation\">";

    public DMGRCategoryCrawler(ServletContext context) {
        super(context);
    }

    public Map<String, String> getCategories(String url) {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);
                document = document.replaceAll(" &amp;#8211; làm mát", "");
//                XMLHelper.writeTestFileDocument(document);
                return domParserForCategory(document);
            }//end if url

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> domParserForCategory(String document) throws XPathExpressionException {
        document = document.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(document);

        Map<String, String> categoryMap = new HashMap<>();

        if (doc != null) {
            XPath xPath = XMLUtils.creatXPath();
            String exp = "//div[@class='menu-danh-muc-san-pham-container']/ul/li/a";

            NodeList tagList = (NodeList) xPath.evaluate(exp, doc, XPathConstants.NODESET);

            if (tagList != null) {
                for (int i = 0; i < tagList.getLength(); i++) {
                    Node tmp = tagList.item(i);
                    String categoryString = tmp.getLastChild().getTextContent().trim();
                    if (categoryString.equals(StringConstant.DMGR_TIVI_STRING)) {
                        categoryString = "TIVI, LOA";
                    }
                    if (categoryString.equals(StringConstant.DMGR_CONDITIONER_STRING)) {
                        categoryString = "ĐIỀU HÒA NHIỆT ĐỘ";
                    }
                    String urlString = tmp.getAttributes().getNamedItem("href").getNodeValue();

                    if (!urlString.equals("") && !categoryString.equals("") && !categoryString.equals(StringConstant.FRIDGE_STRING)) {
                        categoryMap.put(categoryString.toUpperCase(), urlString);
                        System.out.println(categoryString);
                        System.out.println(urlString);
                    }

                }//end for

            } // end if tagList
        } //end if doc

        return categoryMap;
    }

}
