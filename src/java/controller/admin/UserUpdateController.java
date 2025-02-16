/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import DAL.AccountDAO;
import DAL.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author ASUS
 */
@WebServlet(name="UserUpdateController", urlPatterns={"/admin/user_update"})
public class UserUpdateController extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        AccountDAO accountDAO = new AccountDAO();
        RoleDAO roleDAO = new RoleDAO();
        
        Account account = accountDAO.findById(Integer.parseInt(id));
        List<Role> roleList = roleDAO.getAllRole();
        
        request.setAttribute("user", account);
        request.setAttribute("roleList", roleList);
        
        request.getRequestDispatcher("userupdate.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("account_id"));
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String address = request.getParameter("address");
        int gender = Integer.parseInt(request.getParameter("gender"));
        int roleId = Integer.parseInt(request.getParameter("role_id"));
        
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        account.setAccount_id(accountId);
        account.setFullname(fullname);
        account.setEmail(email);
        account.setPhone_number(phoneNumber);
        account.setAddress(address);
        account.setGender(gender);
        account.setRole_id(roleId);
        
        accountDAO.update(account);
        
        response.sendRedirect("userlist");
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
