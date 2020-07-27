/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhbv.dlo.ProductBLO;
import khanhbv.dto.ProductCart;
import khanhbv.entities.Product;
import khanhbv.utils.StringConstant;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "FindGeneratorServlet", urlPatterns = {"/FindGeneratorServlet"})
public class FindGeneratorServlet extends HttpServlet {
    
    private static final String GENERATOR_PAGE = "generator.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = GENERATOR_PAGE;
        try {
            HttpSession session = request.getSession();
            ProductCart cart = (ProductCart) session.getAttribute("CART");
            if (cart == null) {
                cart = new ProductCart();
            }
            
            String[] strQuantity = request.getParameterValues("txtQuantity");
            String[] strHour = request.getParameterValues("txtTime");
            
            String idProduct = request.getParameter("idProduct");
            float totalPower = 0;
            float totalPowerOfProduct = 0;
            int quantity = 0;
            
            if (cart != null) {
                Map<Integer, Product> items = cart.getItems();
                
                if (items.size() != 0) {
                    int id = Integer.parseInt(idProduct);
                    int count = 0;
                    
                    for (Map.Entry<Integer, Product> entry : items.entrySet()) {
                        String quan = strQuantity[count];
                        quantity = Integer.parseInt(quan);
                        
                        Product value = entry.getValue();
                        totalPowerOfProduct = value.getPower() * quantity;
                        totalPower = totalPower + totalPowerOfProduct;
                        count++;
                        
                    }
                    ProductBLO productBLO = new ProductBLO();
                    List<Product> listgenerator = new ArrayList<>();
                    listgenerator = productBLO.getGeneratorByPower(totalPower, StringConstant.QM_GENERATOR_STRING.toUpperCase());
                    session.setAttribute("LISTGENERATORBYPOWER", listgenerator);
                    session.setAttribute("TOTALPOWER", totalPower);
                }
            }
            
        } catch (Exception e) {
//            e.printStackTrace();
            log("Find Generator Servlet: " + e.getMessage());
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
