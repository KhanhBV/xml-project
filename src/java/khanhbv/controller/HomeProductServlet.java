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
import javax.servlet.http.HttpSession;
import khanhbv.dlo.BrandBLO;
import khanhbv.dlo.CategoryBLO;
import khanhbv.dlo.ProductBLO;
import khanhbv.entities.Brand;
import khanhbv.entities.Category;
import khanhbv.entities.Product;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "HomeProductServlet", urlPatterns = {"/HomeProductServlet"})
public class HomeProductServlet extends HttpServlet {

    private static final String ERROR_PAGE = "error.html";
    private static final String HOME_PAGE = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        CategoryBLO categoryBLO = new CategoryBLO();
        ProductBLO productBLO = new ProductBLO();
        BrandBLO brandBLO = new BrandBLO();
        
        List<Category> listCategorys = new ArrayList<>();
        List<Product> listproProducts = new ArrayList<>();
        List<Brand> listBrand = new ArrayList<>();
        try {
            listCategorys = categoryBLO.getAllCategory();
            listproProducts = productBLO.getAllProduct();
            listBrand = brandBLO.getAllBrand();
            HttpSession session = request.getSession();
            if (listCategorys != null & listproProducts != null) {
                

                
                session.setAttribute("LISTCATEGORY", listCategorys);
                session.setAttribute("LISTBRAND", listBrand);
                
                url = HOME_PAGE;
            }
            //get link when you click to page
            session.setAttribute("QUERYSTRING", "DispatcherServlet?&btAction=Home");
            
            String pageNum = request.getParameter("pageNumber");
            int pageNumber = 1;
            if (pageNum != null) {
                pageNumber = Integer.parseInt(pageNum);
            }
            
            // divide 20 product per one page
            int numberItems = listproProducts.size();
            int maxPage = numberItems / 20;
            if (numberItems % 20 != 0) {
                maxPage = maxPage + 1;
            }
            List<Product> resultPage = new ArrayList<>();
            // add product to one page
            if (listproProducts != null) {
                int fromPage = (pageNumber * 20) - 20;
                int endPage = fromPage + 20;
                if (endPage < listproProducts.size()) {
                    for (int i = fromPage; i < endPage; i++) {
                        resultPage.add(listproProducts.get(i));
                    }
                } else {
                    for (int j = fromPage; j < listproProducts.size(); j++) {
                        resultPage.add(listproProducts.get(j));
                    }
                }
            }
            
            session.setAttribute("LISTALLPRODUCT", resultPage);
            request.setAttribute("MAXPAGE", maxPage);
            request.setAttribute("PAGENUMBER", pageNumber);


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