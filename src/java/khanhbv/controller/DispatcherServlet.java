/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vankhanhbui
 */
public class DispatcherServlet extends HttpServlet {

    private static final String HOME_PRODUCT_SERVLET = "HomeProductServlet";
    private static final String SEARCH__SERVLET = "SearchServlet";
    private static final String CHARGE_ELECTRICITY__SERVLET = "ChargeElectricityServlet";
    private static final String CACULATE = "CaculateMoneyServlet";
    private static final String REMOVEITEM = "RemoveItemServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PRODUCT_SERVLET;
        try {
            String action = request.getParameter("btAction");
            if (action == null) {

            } else if (action.equals("Search")) {
                url = SEARCH__SERVLET;
            } else if (action.equals("Add")) {
                url = CHARGE_ELECTRICITY__SERVLET;
            }else if(action.equals("Caculate Electric Money")){
                url = CACULATE;
            }else if(action.equals("Remove")){
                url = REMOVEITEM;
            }

        } catch (Exception e) {
            e.printStackTrace();
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
