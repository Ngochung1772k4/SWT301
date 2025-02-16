/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAL.SuppliersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.ZoneId;
import model.Suppliers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddSupplierController", urlPatterns = {"/admin/addSupplier"})
public class AddSupplierController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String contactInfo = request.getParameter("contactInfo");
        String address = request.getParameter("address");
        
        Suppliers supplier = new Suppliers();
        supplier.setName(name);
        supplier.setContactInfo(contactInfo);
        supplier.setAddress(address);
        supplier.setCreatedAt(new Date());
        SuppliersDAO dbSupplier = new SuppliersDAO();
        dbSupplier.addSupplier(supplier);
        response.sendRedirect("suppliers");
    }

}
