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
import khanhbv.dlo.ProductBLO;
import khanhbv.entities.Brand;
import khanhbv.entities.Category;
import khanhbv.entities.Product;

/**
 *
 * @author vankhanhbui
 */
@WebServlet(name = "AddNewProductServlet", urlPatterns = {"/AddNewProductServlet"})
public class AddNewProductServlet extends HttpServlet {
    private static final String HOME_PAGE = "home.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Category> listCategory = new ArrayList<>();
        List<Brand> listBrand = new ArrayList<>();
        ProductBLO productBLO = new ProductBLO();
        Product productEntity = new Product();
        CategoryBLO categoryBLO = new CategoryBLO();
        BrandBLO brandBLO = new BrandBLO();
        String url = HOME_PAGE;
        try {
             //list category db
            listCategory = categoryBLO.getAllCategory();
            //list brand db
            listBrand = brandBLO.getAllBrand();

            String nameProduct = request.getParameter("productName");
            String power = request.getParameter("capacityProduct");
            String brandName = request.getParameter("brandProduct");
            String categoryName = request.getParameter("nameCategory");
            String imageURL = request.getParameter("imageURL");
            String urlProduct = request.getParameter("urlProduct");

            float capacity = 0;
            if (!power.isEmpty()) {
                capacity = Float.parseFloat(power);
            }

            productEntity.setName(nameProduct);
            productEntity.setPower(capacity);
            if (imageURL.isEmpty()) {
                imageURL = "ĐANG CẬP NHẬT";
            }
            if (urlProduct.isEmpty()) {
                urlProduct = "ĐANG CẬP NHẬT";
            }
            productEntity.setImageURL(imageURL);
            productEntity.setUrl(urlProduct);

            for (int i = 0; i < listCategory.size(); i++) {
                if (categoryName.equals(listCategory.get(i).getName())) {
                    productEntity.setCategoryID(listCategory.get(i));
                }
            }
            for (int j = 0; j < listBrand.size(); j++) {
                if (brandName.equals(listBrand.get(j).getName())) {
                    productEntity.setBrandID(listBrand.get(j));
                }
            }


            productBLO.insertProduct(productEntity);
        } catch(Exception e) {
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
