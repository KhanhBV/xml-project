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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author vankhanhbui
 */
public class DMGRProductLinkByCategoryCrawler extends BaseCrawler {

    private String url;
    private String category;
    private static String beginSyntax = "<div class=\"sh-product-shortcode column-4\">";
    private static String endSyntax = "<div class=\"term-description\">";

    private List<String> productLinkList;

    public DMGRProductLinkByCategoryCrawler(ServletContext context, String url, String category) {
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
                
                
                for (int i = 2; i <= maxPage; i++) {
                    String urlPaging = url + StringConstant.PAGE_SYNTAX_DMGR + i;
                    getProductLinkInPage(urlPaging);
                }//end for maxPage
         
                for (int i = 0; i < productLinkList.size(); i++) {
                    getDetailProduct(productLinkList.get(i), category);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLastPage(String docString) throws XPathExpressionException {
        docString = docString.trim();
        Document doc = XMLUtils.convertStringToXMLDocument(docString);
        int lastPage = 1;
        if (doc != null) {
            String exp = "//ul[@class='page-numbers']//li[last()-1]";
            XPath xPath = XMLUtils.creatXPath();
            Node pagingNode = (Node) xPath.evaluate(exp, doc, XPathConstants.NODE);

            if (pagingNode != null) {
                String maxPage = pagingNode.getTextContent();
                lastPage = Integer.parseInt(maxPage.trim());
//                System.out.println(lastPage);
            }//end pagingNode

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
            String exp = "//div[@class='image-product']/a";
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
       DMGRProductDetailCrawler detailCrawler = new DMGRProductDetailCrawler(null, category, url);
        detailCrawler.getProductDetail();
    }

}
