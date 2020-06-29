/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.crawler;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletContext;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author buivankhanh
 */
public class BaseCrawler {
    private ServletContext context;

    public BaseCrawler(ServletContext context) {
        this.context = context;
    }

    public ServletContext getContext() {
        return context;
    }
    
    protected BufferedReader getBufferedReaderForURL(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36");
//        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36");
        InputStream is = connection.getInputStream();
        if (is == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        return reader;
    }
    
//    protected XMLEventReader parseStringToXMLEventReader(String xmlSection) throws UnsupportedEncodingException, XMLStreamException {
//        byte[] byteArray = xmlSection.getBytes("UTF-8");
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
//        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//        XMLEventReader reader = inputFactory.createXMLEventReader(inputStream);
//        
//        return reader;
//    }
    
}
