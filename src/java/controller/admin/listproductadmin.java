/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import DAL.CategoryDAO;
import DAL.ImagesDAO;
import DAL.ProductDAO;
import DAL.SuppliersDAO;
import DAL.UnitsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Images;
import model.Product;
import model.Suppliers;
import model.Units;

/**
 *
 * @author BT
 */
public class listproductadmin extends HttpServlet {
   
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
            ProductDAO dao = new ProductDAO();
            UnitsDAO dao1 = new UnitsDAO();
            SuppliersDAO dao2 = new SuppliersDAO();
            ImagesDAO dao3 = new ImagesDAO();
            CategoryDAO dao4 = new CategoryDAO();
            
            
            // Lấy danh sách sản phẩm và thông tin liên quan
            List<Product> listProduct = dao.getAllProducts();
            List<Category> listCategory = dao4.getAllCategories();
            List<Suppliers> listSupplier = dao2.getAllSupplier();
            List<Units> listUnit = dao1.getAllUnit();
            
            // Lấy ảnh cho từng sản phẩm
            HashMap<Integer, List<Images>> productImages = new HashMap<>();
            for (Product p : listProduct) {
                 List<Images> images = dao3.getImagesByProductId(p.getProductId());
                productImages.put(p.getProductId(), images);
            }
            
            // Truyền dữ liệu sang JSP
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("listSupplier", listSupplier);
            request.setAttribute("listUnit", listUnit);
            request.setAttribute("productImages", productImages);
            
            request.getRequestDispatcher("productadmin.jsp").forward(request, response);
            
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
