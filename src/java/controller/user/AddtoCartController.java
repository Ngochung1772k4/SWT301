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
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;


/**
 *
 * @author ngoch
 */
@WebServlet(name="AddtoCartController", urlPatterns={"/addtocart"})
public class AddtoCartController extends HttpServlet {
   
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
            out.println("<title>Servlet AddtoCartController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddtoCartController at " + request.getContextPath () + "</h1>");
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
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    Cart cart = null;
    Object o = session.getAttribute("cart");
    if (o != null) {
        cart = (Cart) o;
    } else {
        cart = new Cart();
    }

    String tnum = request.getParameter("num");
    String tid = request.getParameter("id");
    int num, id;
    try {
        num = Integer.parseInt(tnum);
        id = Integer.parseInt(tid);

        ProductDAO dao = new ProductDAO();
        Product p = dao.getProductById(id);
        if (p == null) {
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Product not found.\"}");
            return;
        }

        // Kiểm tra số lượng tồn kho
        if (num > p.getQuantity()) {
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Not enough stock available.\"}");
            return;
        }

        // Cập nhật số lượng tồn kho
        boolean updateSuccess = dao.updateProductQuantity(id, num);
        if (!updateSuccess) {
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Not enough stock available.\"}");
            return;
        }

        int price = p.getDiscountPrice();
        Item t = new Item(p, num, price);
        cart.addItem(t);

        // Lưu giỏ hàng nếu người dùng đã đăng nhập
       

    } catch (NumberFormatException e) {
        response.getWriter().write("{\"status\": \"error\", \"message\": \"Invalid quantity.\"}");
        return;
    }

    List<Item> list = cart.getItems();
    session.setAttribute("cart", cart);
    session.setAttribute("size", list.size());

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write("{\"status\": \"success\", \"size\": " + list.size() + "}");
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
