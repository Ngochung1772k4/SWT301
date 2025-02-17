package controller.user;

import DAL.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Cart;
import model.Item;

@WebServlet(name = "UpdateCartController", urlPatterns = {"/updatecart"})
public class UpdateCartController extends HttpServlet {
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
                int newQuantity = item.getQuantity() + num;
                item.setQuantity(newQuantity);
                if (item.getQuantity() <= 0) {
                    cart.removeItem(productId);
                }
            }
        }
        session.setAttribute("cart", cart);
        session.setAttribute("size", cart.getItems().size());
        response.sendRedirect("cart.jsp");
    }
}