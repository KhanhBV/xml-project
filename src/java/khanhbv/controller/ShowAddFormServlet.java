/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanhbv.dlo.BrandBLO;
import khanhbv.dlo.CategoryBLO;
import khanhbv.entities.Brand;
import khanhbv.entities.Category;
import khanhbv.entities.Product;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "ShowAddFormServlet", urlPatterns = {"/ShowAddFormServlet"})
public class ShowAddFormServlet extends HttpServlet {

    private static final String ADD_NEW_PRODUCT = "addNewProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Category> listCategory = new ArrayList<>();
        List<Brand> listBrand = new ArrayList<>();
        List<Product> listProduct = new ArrayList<>();
        CategoryBLO categoryBLO = new CategoryBLO();
        BrandBLO brandBLO = new BrandBLO();

        String url = ADD_NEW_PRODUCT;
        try {

            //list category db
            listCategory = categoryBLO.getAllCategory();
            //list brand db
            listBrand = brandBLO.getAllBrand();
            request.setAttribute("LISTCATEGORY", listCategory);
            request.setAttribute("LISTBRAND", listBrand);

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
