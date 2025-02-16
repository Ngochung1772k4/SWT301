/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import DAL.*;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

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
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String gender = request.getParameter("gender");
        String date_of_birth = request.getParameter("date_of_birth");
        String account_name = request.getParameter("account_name");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String address = request.getParameter("address");
        if (!password.equals(re_password)) {
            String errorMessage = "Mật khẩu nhập lại không khớp!";
            request.setAttribute("errorMessage", errorMessage);
            this.doGet(request, response);
        } else {
            AccountDAO dao = new AccountDAO();
            if (dao.checkUserExist(account_name) == null) {
 Timestamp created_at = Timestamp.valueOf(LocalDateTime.now());
            Timestamp updated_at = Timestamp.valueOf(LocalDateTime.now());

            // Convert date_of_birth from String to java.sql.Date
            Date dob = Date.valueOf(date_of_birth);

            // Create a new Account object
            Account new_acc = new Account(0, 0, fullname, email, phone_number, address, account_name, password,
                    created_at, updated_at, 0, dob, Integer.parseInt(gender), "");

            // Sign up the user
            dao.signupUser(new_acc);
                String errorMessage = "Tạo tài khoản thành công!";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
String errorMessage = "Tên tài khoản đã tồn tại!";
                request.setAttribute("errorMessage", errorMessage);
                this.doGet(request, response);
            }
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String gender = request.getParameter("gender");
        String date_of_birth = request.getParameter("date_of_birth");
        String account_name = request.getParameter("account_name");
        String address = request.getParameter("address");

        request.setAttribute("fullname", fullname);
        request.setAttribute("email", email);
        request.setAttribute("phone_number", phone_number);
        request.setAttribute("gender", gender);
        request.setAttribute("date_of_birth", date_of_birth);
        request.setAttribute("address", address);
        request.setAttribute("account_name", account_name);
        request.getRequestDispatcher("register.jsp").forward(request, response);
        response.sendRedirect("register.jsp");
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