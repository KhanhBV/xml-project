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
import javax.servlet.http.HttpSession;
import khanhbv.dlo.BrandBLO;
import khanhbv.dlo.ProductBLO;
import khanhbv.dto.ProductCart;
import khanhbv.entities.Brand;
import khanhbv.entities.Product;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "ChargeElectricityServlet", urlPatterns = {"/ChargeElectricityServlet"})
public class ChargeElectricityServlet extends HttpServlet {
    private static final String HOME_PAGE = "home.jsp";
    private final String ERROR_PAGE = "error.html";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        try {
            HttpSession session = request.getSession();
              ProductCart cart = (ProductCart) session.getAttribute("CART");
            if (cart == null) {
                cart = new ProductCart();
            }
            String id = request.getParameter("idProduct");
            String nameCate = request.getParameter("nameCategory");
            String nameBrand = request.getParameter("nameBrand");
            String txtNameSearch = request.getParameter("txtNameSearch");
            String pageNumber = request.getParameter("pageNumber");
            int idProduct = Integer.parseInt(id);
            
                url = "DispatcherServlet?nameCategory=" + nameCate + "&btAction=Search"
                        + "&nameBrand=" + nameBrand + "&txtNameSearch=" + txtNameSearch;
            
            
            cart.addItemsToCaculate(idProduct);
            session.setAttribute("CART", cart);
            
        } catch(Exception e) {
            log("ChargeElectric_Servlet: " + e.getMessage());
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
