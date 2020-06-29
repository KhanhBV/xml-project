/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author buivankhanh
 */
public class XMLHelper {

    public static String findHTMLToCrawl(BufferedReader reader, String beginSyntax,
            String endSyntax) throws IOException {

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
                }//end isSTart and line
                if (isStart) {
                    document = document + line.trim();
                } //end isStart
            }//end while
            document = document + "</document>";
            XmlSyntaxChecker checker = new XmlSyntaxChecker();
            document = checker.check(document);
            return document;
        }//end if reader

        return null;

    }

    public static void writeTestFileDocument(String document) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("testDocumne.txt"))) {

            bw.write(document);
            // không cần đóng BufferedWriter (nó đã tự động đóng)
            // bw.close();
            System.out.println("Xong");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
