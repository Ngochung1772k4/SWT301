/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import DAL.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Orders;

/**
 *
 * @author ngoch
 */
@WebServlet(name="listOrderAdmin", urlPatterns={"/admin/listOrderAdmin"})
public class listOrderAdmin extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String status = request.getParameter("od_status");
        OrderDAO dao = new OrderDAO();
        List<Orders> list_order = new ArrayList<>();
        List<Orders> new_list_order = new ArrayList<>();
        if (status == null) {
            list_order = dao.getOrder();
        } else if (status.equals("0")) {
            list_order = dao.getOrderbyStatus("0");
        } else if (status.equals("1")) {
            list_order = dao.getOrderbyStatus("1");
        } else if (status.equals("2")) {
            list_order = dao.getOrderbyStatus("2");
        } else if (status.equals("3")) {
            list_order = dao.getOrderbyStatus("3");
        }

        for (int i = list_order.size() - 1; 0 <= i; i--) {
            new_list_order.add(list_order.get(i));
        }
        request.setAttribute("listOrder", new_list_order);
        System.out.println("Số lượng đơn hàng: " + list_order.size()); // Thêm dòng này
        request.getRequestDispatcher("/admin/listorder-admin.jsp").forward(request, response);
       
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
