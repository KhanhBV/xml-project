/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.crawler;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import khanhbv.utils.XMLUtils;
import khanhbv.utils.XmlSyntaxChecker;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author vankhanhbui
 */
public class DienMayLinhBrandCrawler extends BaseCrawler {

    public DienMayLinhBrandCrawler(ServletContext context) {
        super(context);
    }
    private List<String> listBrand = null;
    private static String beginSyntax = "<div class=\"slim-scroll-div\"";
    private static String endSyntax = "<span class=\"text\">ĐỒ DÙNG GIA ĐÌNH</span>";

    public List<String> getCategories(String url) {
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
                    return domParserForBrand(document);
//                    return domParserForCategory(document);
                }//end if reader
            }//end if url

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<String> domParserForBrand(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);

        if (docString != null) {
            XPath xPath = XMLUtils.creatXPath();
            String exp = "//li[a[contains(text(),'Thương hiệu') or text()='Quạt điều hoà' or text()='Tủ đông' or text()='Tủ mát']]/ul/li/a";
            NodeList brandNodeList = (NodeList) xPath.evaluate(exp, doc, XPathConstants.NODESET);

            if (brandNodeList != null) {
                for (int i = 0; i < brandNodeList.getLength(); i++) {
                    Node tmp = brandNodeList.item(i);
                    String brandString = tmp.getTextContent();
                    boolean test = false;
                    if (!brandString.isEmpty()) {
                        
//                        System.out.println(brandString + "\n");
                        if (listBrand == null) {
                            listBrand = new ArrayList<>();
                        }//end if listBrand == null
                        if (listBrand.size() == 0) {
                            listBrand.add(brandString);
                        } else {
                            for (int j = 0; j < listBrand.size(); j++) {
                                if (brandString.trim().equals(listBrand.get(j)) || brandString.equals("Thương hiệu")) {
                                    test = true;
                                    break;
                                }//end if brandString
                                
                            } //end for listBrand
                            if (!test) {
                                listBrand.add(brandString);
//                                System.out.println(brandString);
                            }
                        }//end else
                    }//end if bradString is not empty

                } //end for brandNodeList
                
            }
        }

        return listBrand;
    }

}
