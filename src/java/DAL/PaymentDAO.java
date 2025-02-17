package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Payment;
import java.sql.ResultSet;

public class PaymentDAO extends DBcontext {

    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payments (order_id, account_id, payment_method, status, amount_paid, transaction_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

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

    public Payment getPaymentByOrderId(int orderId) {
        String sql = "SELECT * FROM Payments WHERE order_id = ?";
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("order_id"),
                        rs.getInt("account_id"),
                        rs.getString("payment_method"),
                        rs.getString("status"),
                        rs.getString("transaction_id"),
                        rs.getDouble("amount_paid")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
