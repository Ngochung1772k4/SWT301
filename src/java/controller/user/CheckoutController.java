package controller.user;

import DAL.OrderDAO;
import DAL.CartDAO;
import DAL.PaymentDAO;
import DAL.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.Payment;
import model.Product;
import model.Item;

@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }

        Account account = null;
        Object a = session.getAttribute("account");
        if (a != null) {
            account = (Account) a;

            
            String fullname = request.getParameter("fullname");
            String phoneNumber = request.getParameter("phone_number");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            String paymentMethod = request.getParameter("payment_method");

           
            account.setFullname(fullname);
            account.setPhone_number(phoneNumber);
            account.setEmail(email);
            account.setAddress(address);

          
            ProductDAO productDAO = new ProductDAO();
            OrderDAO orderDAO = new OrderDAO();
            int orderId = orderDAO.addOrder(account, cart, note);

            if (orderId != -1) {
             
                Payment payment = new Payment();
                payment.setOrderId(orderId);
                payment.setAccountId(account.getAccount_id());
                payment.setPaymentMethod(paymentMethod);
                payment.setAmountPaid(cart.getTotalMoney());
                payment.setStatus("Pending");

             
                if ("ATM".equals(paymentMethod)) {
                    String cardNumber = request.getParameter("card_number");
                    String expiryDate = request.getParameter("expiry_date");
                    String cvv = request.getParameter("cvv");

                    String transactionId = "TXN" + System.currentTimeMillis();
                    payment.setTransactionId(transactionId);
                }

               
                PaymentDAO paymentDAO = new PaymentDAO();
                paymentDAO.addPayment(payment);

                
                for (Item item : cart.getItems()) {
                    productDAO.updateProductQuantity(
                        item.getProduct().getProductId(),
                        item.getQuantity()
                    );
                }

                
                CartDAO cartDAO = new CartDAO();
                cartDAO.deleteCartByAccountId(account.getAccount_id());

             
                session.removeAttribute("cart");
                session.setAttribute("size", 0);

              
                response.sendRedirect("checkout_success.jsp");
            } else {
                
                response.sendRedirect("checkout_failed.jsp");
            }
        } else {
           
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}