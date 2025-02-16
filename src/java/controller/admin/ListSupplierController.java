/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAL.SuppliersDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Suppliers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ListSupplierController", urlPatterns = {"/admin/suppliers"})
public class ListSupplierController extends HttpServlet {

    private final int PAGE_SIZE = 10;
    private final SuppliersDAO dbSupplier = new SuppliersDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String contactInfo = request.getParameter("contact_info");
        String address = request.getParameter("address");

        if (name == null) {
            name = "";
        }
        if (contactInfo == null) {
            contactInfo = "";
        }
        if (address == null) {
            address = "";
        }

        int index = 1;
        String raw_index = request.getParameter("index");
        if (raw_index != null && !raw_index.isBlank()) {
            try {
                index = Integer.parseInt(raw_index);
            } catch (Exception e) {
                index = 1;
            }
        }

        int totalSuppliers = dbSupplier.getTotalSuppliers();
        int total = (totalSuppliers % PAGE_SIZE == 0) ? (totalSuppliers / PAGE_SIZE) : ((totalSuppliers / PAGE_SIZE) + 1);
        request.setAttribute("index", index);
        request.setAttribute("total", total);
        List<Suppliers> suppliers = dbSupplier.getSuppliersByPagingAndNameContactAddress(index, PAGE_SIZE, name, contactInfo, address);

        request.setAttribute("name", name);
        request.setAttribute("contact_info", contactInfo);
        request.setAttribute("address", address);
        request.setAttribute("suppliers", suppliers);
        request.getRequestDispatcher("supplier-manager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
