/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import DAL.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Images;
import model.Product;

/**
 *
 * @author BT
 */
@WebServlet(name="addProductAdmin", urlPatterns={"/addProductAdmin"})
public class addProductAdmin extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Lấy thông tin sản phẩm từ request
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            Integer supplierId = request.getParameter("supplier_id") != null ? Integer.parseInt(request.getParameter("supplier_id")) : null;
            Integer unitId = request.getParameter("unit_id") != null ? Integer.parseInt(request.getParameter("unit_id")) : null;
            String name = request.getParameter("name");
            int discount = Integer.parseInt(request.getParameter("discount"));
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            Integer weight = request.getParameter("weight") != null ? Integer.parseInt(request.getParameter("weight")) : null;
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
            Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
            int deleted = 0;

            // Tạo đối tượng Product
            Product product = new Product();
            product.setCategoryId(categoryId);
            product.setSupplierId(supplierId);
            product.setUnitId(unitId);
            product.setName(name);
            product.setDiscount(discount);
            product.setDescription(description);
            product.setPrice(price);
            product.setWeight(weight);
            product.setQuantity(quantity);
            product.setCreatedAt(createdAt);
            product.setUpdatedAt(updatedAt);
            product.setDeleted(deleted);

            // Lấy thông tin ảnh từ request
            List<Images> images = new ArrayList<>();
            String[] imageUrls = request.getParameterValues("imageUrl");
            String[] altTexts = request.getParameterValues("altText");

            if (imageUrls != null && altTexts != null) {
                for (int i = 0; i < imageUrls.length; i++) {
                    Images image = new Images();
                    image.setUrl(imageUrls[i]);
                    image.setAltText(altTexts[i]);
                    image.setCreatedAt(createdAt); // Sử dụng thời gian tạo của sản phẩm
                    images.add(image);
                }
            }

            // Gọi DAO để thêm sản phẩm và ảnh vào database
            ProductDAO dao = new ProductDAO();
            boolean success = dao.insertProduct(product, images);

            if (success) {
                // Nếu thêm thành công, chuyển hướng về trang danh sách sản phẩm
                response.sendRedirect("listproductadmin");
            } else {
                // Nếu có lỗi, chuyển hướng về trang lỗi
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
