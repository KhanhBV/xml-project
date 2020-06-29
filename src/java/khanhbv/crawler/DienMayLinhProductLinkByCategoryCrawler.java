/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.crawler;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
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
public class DienMayLinhProductLinkByCategoryCrawler extends BaseCrawler {

    private String url;
    private String category;
    private static String beginSyntax = "<div id=\"ajax-search\">";
    private static String endSyntax = "<div id=\"pvt-footer\" class=\"outer\">";

    private List<String> productLinkList;

    public DienMayLinhProductLinkByCategoryCrawler(ServletContext context, String url, String category) {
        super(context);
        this.url = url;
        this.category = category;
    }

    public void getProductLink() {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);
//                XMLHelper.writeTestFileDocument(document);

                int maxPage = getLastPage(document);
                domParserProductLink(document);
                for (int i = 1; i <= maxPage; i++) {
                    String urlPaging = url + StringConstant.PAGE_SYNTAX_DML + i;
                    getProductLinkInPage(urlPaging);
                }//end for maxPage

                for (int i = 0; i < productLinkList.size(); i++) {
                    getDetailProduct(productLinkList.get(i), category);
                }//end for product Link

            }
        } catch (Exception e) {
            e.printStackTrace();
        }//end catch
    }

    public int getLastPage(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);
        int lastPage = 1;
        if (doc != null) {
            String exp = "//div[@class='nav-page']//li[last()]//a";
            XPath xPath = XMLUtils.creatXPath();
            Node pagingNode = (Node) xPath.evaluate(exp, doc, XPathConstants.NODE);
            if (pagingNode != null) {
//                Node maxPageNode = pagingNode.getLastChild(); //get last node
                if (pagingNode != null) {
                    String maxPageString = pagingNode.getAttributes().getNamedItem("data-ci-pagination-page").getNodeValue(); //get value last child
                    System.out.println(maxPageString);
                    if (!maxPageString.isEmpty()) {
                        lastPage = Integer.parseInt(maxPageString);
                        return lastPage;

                    }//end if maxPageString
                }//end pagingNode
            }//end if pagingNode
        }//end if doc

        return lastPage;

    }

    public void getProductLinkInPage(String url) {
        BufferedReader reader = null;
        try {
            if (url != null) {
                reader = getBufferedReaderForURL(url);
                String document = XMLHelper.findHTMLToCrawl(reader, beginSyntax, endSyntax);
                domParserProductLink(document);
            }

        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public void domParserProductLink(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);
        if (productLinkList == null) {
            productLinkList = new ArrayList<>();
        }
        if (doc != null) {
            XPath xPath = XMLUtils.creatXPath();
            String exp = "//a[@itemprop='url']";
            NodeList listLink = (NodeList) xPath.evaluate(exp, doc, XPathConstants.NODESET);

            if (listLink.getLength() > 0) {
                for (int i = 0; i < listLink.getLength(); i++) {
                    String link = listLink.item(i).getAttributes().getNamedItem("href").getNodeValue();
                    if (!link.isEmpty()) {
//                        System.out.println(link);
                        productLinkList.add(link);
                    }
                }
            }
        }
    }

    public void getDetailProduct(String url, String category) {
        DMLProductDetailCrawler detailCrawler = new DMLProductDetailCrawler(null, category, url);
        detailCrawler.getProductDetail();
    }
}
