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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Role;

/**
 *
 * @author BT
 */
public class UserListController extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserListController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserListController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        AccountDAO dao = new AccountDAO();
        // chuyển dữ liệu từ DB thành danh sách List<Accont>
        List<Account> list = dao.getAllAccount();
        RoleDAO roleDao = new RoleDAO();
        List<Role> roleList = roleDao.getAllRole(); // Lấy danh sách vai trò

        // Tạo một Map để ánh xạ role_id với role_name
        Map<Integer, String> roleMap = new HashMap<>();
        for (Role role : roleList) {
            roleMap.put(role.getRole_id(), role.getRole_name());
        }

        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("listRe", list); // Danh sách tài khoản
            request.setAttribute("roleMap", roleMap); // Ánh xạ role_id -> role_name

            request.getRequestDispatcher("userList.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.setAttribute("roleList", roleList);
            request.getRequestDispatcher("usercreate.jsp").forward(request, response);
        }
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
        request.setCharacterEncoding("UTF-8");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int role_id = Integer.parseInt(request.getParameter("role"));

        Account newAccount = new Account(role_id, fullname, email, username, password);
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.create(newAccount);

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
