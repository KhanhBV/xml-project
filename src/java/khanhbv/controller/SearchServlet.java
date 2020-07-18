/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhbv.dlo.ProductBLO;
import khanhbv.dto.ProductTestDTO;
import khanhbv.entities.Product;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String HOME_PAGE = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        try {
            String txtNameSearch = request.getParameter("txtNameSearch");
            String nameCategory = request.getParameter("nameCategory");
            String nameBrand = request.getParameter("nameBrand");
            HttpSession session = request.getSession();

            ProductBLO productBLO = new ProductBLO();
            List<Product> listProductEntity;
            List<ProductTestDTO> listProductDTO;
            url = HOME_PAGE;
            if (txtNameSearch.isEmpty() && nameCategory.equals("0") && nameBrand.equals("0")) {
                listProductEntity = productBLO.getAllProduct();
                session.setAttribute("LISTALLPRODUCT", listProductEntity);
            } else {
                if (nameBrand.equals("0")) {
                    nameBrand = "";
                }
                if (nameCategory.equals("0")) {
                    nameCategory = "";
                }
                listProductDTO = productBLO.searchProductByCaAndBrand(nameBrand, nameCategory, txtNameSearch);
                session.setAttribute("LISTALLPRODUCT", listProductDTO);
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
