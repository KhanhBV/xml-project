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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author vankhanhbui
 */
public class QMCategoryCrawler extends BaseCrawler {

    private static String beginSyntax = "<ul class=\"megamenu flipping\">";
    private static String endSyntax = "<div id=\"rev_slider_1_1_wrapper\" ";

    public QMCategoryCrawler(ServletContext context) {
        super(context);
    }

    public Map<String, String> getCategories(String url) {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);

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
            String exp = "//div/a[contains(text(),'"
                    + StringConstant.QM_GENERATOR_STRING
                    + "')]";

            
            Node tag = (Node) xPath.evaluate(exp, doc, XPathConstants.NODE);

            if (tag != null) {
                String urlStr = tag.getAttributes().getNamedItem("href").getNodeValue();
                String categoryStr = tag.getTextContent().trim();
                if (!urlStr.isEmpty() && !urlStr.isEmpty()) {
                    categoryMap.put(categoryStr.toUpperCase(), StringConstant.QM_URL_DOMAIN_NAME + urlStr);
//                    System.out.println("category: " + categoryStr.toUpperCase());
//                    System.out.println("url: " + urlStr);
                }
            }
        } //end if doc

        return categoryMap;
    }

}
