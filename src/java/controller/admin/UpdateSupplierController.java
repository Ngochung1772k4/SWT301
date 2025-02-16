/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAL.SuppliersDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Suppliers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateSupplierController", urlPatterns = {"/admin/updateSupplier"})
public class UpdateSupplierController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);

        SuppliersDAO dbSupplier = new SuppliersDAO();
        Suppliers supplier = dbSupplier.getSupplierById(id);

        Gson gson = new Gson();
        response.getWriter().print(gson.toJson(supplier));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id = Integer.parseInt(raw_id);
        String name = request.getParameter("name");
        String contact_info = request.getParameter("contact_info");
        String address = request.getParameter("address");
        String created_at = request.getParameter("created_at");
        SuppliersDAO dbSupplier = new SuppliersDAO();
        Suppliers supplier = dbSupplier.getSupplierById(id);
        supplier.setName(name);
        supplier.setContactInfo(contact_info);
        supplier.setAddress(address);
        dbSupplier.updateSupplier(supplier);
        response.sendRedirect("suppliers");
    }
}
