package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Payment;

public class PaymentDAO extends DBcontext {


    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payments (order_id, account_id, payment_method, status, amount_paid, transaction_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            
            st.setInt(1, payment.getOrderId());
            st.setInt(2, payment.getAccountId());
            st.setString(3, payment.getPaymentMethod());
            st.setString(4, payment.getStatus());
            st.setDouble(5, payment.getAmountPaid());
            st.setString(6, payment.getTransactionId());
            
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}