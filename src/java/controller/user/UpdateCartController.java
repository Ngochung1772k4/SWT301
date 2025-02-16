/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import DAL.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Item;

/**
 *
 * @author ngoch
 */
@WebServlet(name="UpdateCartController", urlPatterns={"/updatecart"})
public class UpdateCartController extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateCartController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCartController at " + request.getContextPath () + "</h1>");
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
    HttpSession session = request.getSession();
    Cart cart = (Cart) session.getAttribute("cart");
    int productId = Integer.parseInt(request.getParameter("id"));
    int num = Integer.parseInt(request.getParameter("num"));

    if (cart != null) {
        Item item = cart.getItemById(productId);
        if (item != null) {
            ProductDAO dao = new ProductDAO();
            int delta = num;

            if (delta > 0) {
                // Kiểm tra nếu có thể thêm số lượng
                boolean updateSuccess = dao.updateProductQuantity(productId, delta);
                if (!updateSuccess) {
                    session.setAttribute("errorMessage", "Not enough stock available.");
                    response.sendRedirect("cart.jsp");
                    return;
                }
            } else {
                // Trả lại số lượng nếu giảm
                dao.updateProductQuantity(productId, delta);
            }

            // Cập nhật số lượng trong giỏ hàng
            item.setQuantity(item.getQuantity() + delta);
            if (item.getQuantity() <= 0) {
                cart.removeItem(productId);
            }
        }
    }

    session.setAttribute("cart", cart);
    session.setAttribute("size", cart.getItems().size());
    response.sendRedirect("cart.jsp");
}

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
