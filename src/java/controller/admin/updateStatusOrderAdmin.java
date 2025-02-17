package controller.admin;

import DAL.OrderDAO;
import DAL.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.OrderDetails;

@WebServlet(name = "updateStatusOrderAdmin", urlPatterns = {"/admin/updateStatusOrderAdmin"})
public class updateStatusOrderAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String order_id = request.getParameter("orderId");
        String status = request.getParameter("status");
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();

        if (status != null) {
            int newStatus = Integer.parseInt(status);
            orderDAO.changeStatusOrder(order_id, newStatus);

            // Restore product quantity if order is canceled (status = 3)
            if (newStatus == 3) {
                List<OrderDetails> orderDetails = orderDAO.getDetail(order_id);
                for (OrderDetails detail : orderDetails) {
                    int productId = detail.getProductId();
                    int amount = detail.getAmount();
                    productDAO.restoreProductQuantity(productId, amount);
                }
            }

            String errorMessage = "Cập nhật thành công!";
            request.getSession().setAttribute("errorMessage", errorMessage);
        } else {
            String errorMessage = "Cập nhật không thành công!";
            request.getSession().setAttribute("errorMessage", errorMessage);
        }
        response.sendRedirect("listOrderAdmin");
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