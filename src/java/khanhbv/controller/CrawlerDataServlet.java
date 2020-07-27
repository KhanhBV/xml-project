/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanhbv.main.CrawlerDMGRService;
import khanhbv.main.CrawlerDMLService;
import khanhbv.main.CrawlerQLMService;
import khanhbv.main.DMRCrawlerService;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "CrawlerDataServlet", urlPatterns = {"/CrawlerDataServlet"})
public class CrawlerDataServlet extends HttpServlet {

    private static final String HOME_PRODUCT_SERVLET = "HomeProductServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PRODUCT_SERVLET;
        try {
            CrawlerDMLService dmlService = new CrawlerDMLService();
            dmlService.crawlDML();
            DMRCrawlerService dmrService = new DMRCrawlerService();
            dmrService.crawlDMR();

            CrawlerDMGRService dmgrService = new CrawlerDMGRService();
            dmgrService.crawlDMGR();

            CrawlerQLMService QMCrawlerService = new CrawlerQLMService();
            QMCrawlerService.crawlCategory();
        } catch (Exception e) {
            log("CRAWLER_DATA_SERVLET: " + e.getMessage());

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
