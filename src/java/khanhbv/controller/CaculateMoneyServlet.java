/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhbv.dto.ProductCart;
import khanhbv.entities.Product;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "CaculateMoneyServlet", urlPatterns = {"/CaculateMoneyServlet"})
public class CaculateMoneyServlet extends HttpServlet {

    private static final String HOME_PAGE = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = HOME_PAGE;
        try {
            HttpSession session = request.getSession();
            ProductCart cart = (ProductCart) session.getAttribute("CART");
            if (cart == null) {
                cart = new ProductCart();
            }
            String[] strQuantity = request.getParameterValues("txtQuantity");
            String[] strHour = request.getParameterValues("txtTime");

            String idProduct = request.getParameter("idProduct");
            int id = Integer.parseInt(idProduct);

            float totalMoney = 0;
            float usePower = 0;
            float totalPower = 0;
            int quantity = 0;
            float time = 0;
            String strCapacity = "";
            String strTotalMoney = "";
            if (cart != null) {
                Map<Integer, Product> items = cart.getItems();
                if (items != null) {
                    float totalPowerOneDay = 0;
                    int count = 0;
                    for (Map.Entry<Integer, Product> entry : items.entrySet()) {

                        String quan = strQuantity[count];
                        quantity = Integer.parseInt(quan);
                        String hour = strHour[count];
                        time = Float.parseFloat(hour);

                        Product value = entry.getValue();
                        usePower = (float) (value.getPower() * quantity * time);

                        totalPowerOneDay = totalPowerOneDay + usePower;
                        System.out.println("total power day:" + totalPowerOneDay);
                        count++;
                    }
                    totalPower = (float) (totalPowerOneDay * 30);
                    totalMoney = (int) cart.caculateElectric(totalPower);
                    strTotalMoney = String.format("%.2f", totalMoney);
                    strCapacity = String.format("%.2f", totalPower);
                }
            }

            session.setAttribute("CAPA", strCapacity);
            session.setAttribute("MONEY", strTotalMoney);
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
