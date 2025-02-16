/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAL.ProductDAO;
import DAL.SuppliersDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckDeleteSupplierController", urlPatterns = {"/admin/checkDeleteSupplier"})
public class CheckDeleteSupplierController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);
        SuppliersDAO dbSupplier = new SuppliersDAO();
        ProductDAO dbProduct = new ProductDAO();
        List<Product> products = dbProduct.getProductsBySupplierId(id);
        Gson gson = new Gson();
        if (!products.isEmpty()) {
            response.setStatus(400);
        } else {
            response.setStatus(200);
        }
        response.getWriter().print(gson.toJson(products.size()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
