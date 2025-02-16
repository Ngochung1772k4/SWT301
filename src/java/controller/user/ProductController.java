
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAL.CategoryDAO;
import DAL.ImagesDAO;
import DAL.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Images;
import model.Product;

/**
 *
 * @author ngoch
 */
@WebServlet(name = "ProductController", urlPatterns = {"/productController"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ProductDAO productDAO = new ProductDAO();
        ImagesDAO imagesDAO = new ImagesDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        try {

            List<Category> listCate = categoryDAO.getAllCategories();
            request.setAttribute("listCate", listCate);

            String txtSearch = request.getParameter("txt");
            String categoryIdParam = request.getParameter("categoryId");

            List<Product> products;

            if ((txtSearch != null && !txtSearch.trim().isEmpty())
                    && (categoryIdParam != null && !categoryIdParam.trim().isEmpty())) {

                try {
                    int categoryId = Integer.parseInt(categoryIdParam.trim());
                    products = productDAO.searchProductsByNameAndCategory(txtSearch.trim(), categoryId);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid category ID: " + categoryIdParam);
                }

            } else if (txtSearch != null && !txtSearch.trim().isEmpty()) {
                products = productDAO.searchProductsByName(txtSearch.trim());
            } else if (categoryIdParam != null && !categoryIdParam.trim().isEmpty()) {
                try {
                    int categoryId = Integer.parseInt(categoryIdParam.trim());
                    products = productDAO.getProductsByCategory(categoryId);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid category ID: " + categoryIdParam);
                }
            } else {
                products = productDAO.getAllProducts();
            }
              Map<Integer, List<Images>> productImages = new HashMap<>();
            for (Product product : products) {
                List<Images> images = imagesDAO.getImagesByProductId(product.getProductId());
                productImages.put(product.getProductId(), images);
            }
            request.setAttribute("productImages", productImages);
            request.setAttribute("products", products);
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
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