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
public class DienMayLinhCategoriesCrawler extends BaseCrawler {

    public DienMayLinhCategoriesCrawler(ServletContext context) {
        super(context);
    }

    private static String beginSyntax = "<ul class=\"primary-menu\">";
    private static String endSyntax = "<a href=\"https://dienmaylinh.vn/-do-dung-gia-dinh\" target=\"_self\">";

    public Map<String, String> getCategories(String url) {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String line = "";
                String document = "<document>";
                boolean isStart = false;

                if (reader != null) {
                    //get html fragment
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(beginSyntax)) {
                            isStart = true;
                        }//end if begin Syntax

                        if (isStart && line.contains(endSyntax)) {
                            break;
                        }
                        if (isStart) {

                            document = document + line.trim();

                        }
                    }//end while
                    document = document + "</document>";
                    XmlSyntaxChecker checker = new XmlSyntaxChecker();
                    document = checker.check(document);

//                    XMLHelper.writeTestFileDocument(document);
                    return domParserForCategory(document);
                }//end if reader
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
            String exp = "//a[span[@class='text']]";

            NodeList tagList = (NodeList) xPath.evaluate(exp, doc, XPathConstants.NODESET);

            if (tagList != null) {
                for (int i = 0; i < tagList.getLength(); i++) {
                    Node tmp = tagList.item(i);
                    String categoryString = tmp.getLastChild().getTextContent().trim();
                    String urlString = tmp.getAttributes().getNamedItem("href").getNodeValue();
                    

                    if (!urlString.equals("") && !categoryString.equals("") && !categoryString.equals(StringConstant.DML_FRIDGE_STRING)) {
                        categoryMap.put(categoryString, urlString);
                    }

                }//end for

            } // end if tagList
        } //end if doc

        return categoryMap;
    }

}
