/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import DAL.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Asus
 */
@WebServlet(name = "UpdateUserController", urlPatterns = {"/view/user/updateUser"})
public class UpdateUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form data
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        String user_email = request.getParameter("user_email");
        String user_phonenumber = request.getParameter("user_phonenumber");
        String user_address = request.getParameter("user_address");

        // Create a new Account object with the updated information
        Account user = new Account();
        user.setAccount_id(Integer.parseInt(user_id));
        user.setFullname(user_name);
        user.setEmail(user_email);
        user.setPhone_number(user_phonenumber);
        user.setAddress(user_address);

        // Update the user in the database
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();

        try {
            // Perform the update
            dao.updateUser(user);

            // Retrieve the updated user information
            Account user_updated = dao.findById(Integer.parseInt(user_id));

            // Update the session with the new user details
            session.removeAttribute("account");
            session.setAttribute("account", user_updated);

            // Redirect to the profile page
            response.sendRedirect(request.getContextPath() + "/view/user/detail-user.jsp");
        } catch (Exception e) {
            // Handle update failure
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update user information.");
            request.getRequestDispatcher("/view/user/detail-user.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}