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
import model.Product;

@WebServlet(name = "AddtoCartController", urlPatterns = {"/addtocart"})
public class AddtoCartController extends HttpServlet {

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
        try {
            int num = Integer.parseInt(request.getParameter("num"));
            int id = Integer.parseInt(request.getParameter("id"));
            ProductDAO dao = new ProductDAO();
            Product p = dao.getProductById(id);
            if (p == null) {
                sendError(response, "Sản phẩm không tồn tại.");
                return;
            }
            int existingQuantity = 0;
            for (Item item : cart.getItems()) {
                int productId = item.getProduct().getProductId();
                if (productId == id) {
                    existingQuantity = item.getQuantity();
                    break;
                }
            }
            if (existingQuantity + num > p.getQuantity()) {
                sendError(response, "Không đủ hàng trong kho. Bạn đã có " + existingQuantity + " sản phẩm trong giỏ.");
                return;
            }
            cart.addItem(new Item(p, num, p.getDiscountPrice()));
            session.setAttribute("cart", cart);
            session.setAttribute("size", cart.getItems().size());
            sendSuccess(response, cart.getItems().size());

        } catch (NumberFormatException e) {
            sendError(response, "Số lượng không hợp lệ.");
        }
    }
    private void sendSuccess(HttpServletResponse response, int size) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"status\": \"success\", \"size\": %d}", size));
    }
    private void sendError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"status\": \"error\", \"message\": \"%s\"}", message));
    }
}
