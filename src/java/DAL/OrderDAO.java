package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import model.Account;
import model.Cart;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Images;
import model.Item;
import model.OrderDetails;
import model.Orders;
import model.Product;

/**
 *
 * @author ngoch
 */
public class OrderDAO extends DBcontext {

    public int addOrder(Account a, Cart cart, String note) {
    int orderId = -1; 
    try {
        Connection conn = DBcontext.getConnection();
        String sql = "INSERT INTO Orders (account_id, coupon_id, fullname, email, phone_number, address, order_date, status, total_money, note) "
                   + "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        st.setInt(1, a.getAccount_id());
        st.setObject(2, cart.getAppliedCoupon() != null ? cart.getAppliedCoupon().getCouponId() : null);
        st.setString(3, a.getFullname());
        st.setString(4, a.getEmail());
        st.setString(5, a.getPhone_number());
        st.setString(6, a.getAddress());
        st.setInt(7, 0); 
        st.setDouble(8, cart.getTotalMoney());
        st.setString(9, note); 
        st.executeUpdate();

        
        try (ResultSet rs = st.getGeneratedKeys()) {
            if (rs.next()) {
                orderId = rs.getInt(1); 
            }
        }

       
        if (orderId != -1) {
            for (Item i : cart.getItems()) {
                String sql2 = "INSERT INTO Orders_Details (order_id, product_id, price, amount) VALUES (?, ?, ?, ?)";
                PreparedStatement st2 = conn.prepareStatement(sql2);
                st2.setInt(1, orderId);
                st2.setInt(2, i.getProduct().getProductId());
                st2.setInt(3, i.getDiscountPrice());
                st2.setInt(4, i.getQuantity());
                st2.executeUpdate();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return orderId; 
}
 public List<Orders> getOrder() {
        List<Orders> list = new ArrayList<>();
        String query = "SELECT * FROM Orders";

        try (Connection conn = DBcontext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getTimestamp(9), rs.getInt(10), rs.getInt(11)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Orders> getOrderbyStatus(String status) {
    String query = "SELECT * FROM Orders WHERE status = ?";
    List<Orders> list = new ArrayList<>();
    
    try (Connection conn = DBcontext.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        
       ps.setInt(1, Integer.parseInt(status));
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Orders(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getTimestamp(9), rs.getInt(10), rs.getInt(11)));
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
public void changeStatusOrder(String order_id, int status) {
    String query = "UPDATE dbo.Orders SET [status] = ? WHERE order_id = ?";
    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        
        ps.setInt(1, status);
        ps.setString(2, order_id);
        ps.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  public Orders getOrderbyID(String order_id) {
        String query = "SELECT * FROM dbo.Orders WHERE order_id = ?";
        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, order_id); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Orders(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getTimestamp(9),
                        rs.getInt(10), rs.getInt(11)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null;
    }
    



  public List<OrderDetails> getDetail(String order_id) {
    List<OrderDetails> list = new ArrayList<>();
    String query = "SELECT od.order_id, od.product_id, od.price, od.amount, p.name, "
                 + "(SELECT TOP 1 url FROM Images WHERE product_id = p.product_id ORDER BY image_id) AS imageUrl "
                 + "FROM Orders_Details od "
                 + "JOIN Product p ON od.product_id = p.product_id "
                 + "WHERE od.order_id = ?";
    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, order_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String imageUrl = rs.getString("imageUrl");
            // Xử lý trường hợp không có ảnh
            if (imageUrl == null) {
                imageUrl = "/images/placeholder.jpg"; // Đặt ảnh mặc định
            }
            list.add(new OrderDetails(
                rs.getInt("order_id"),
                    
                rs.getInt("price"),
                rs.getInt("amount"),
                rs.getString("name"),
                imageUrl,
                    rs.getInt("product_id") 
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public int getLastestOrderID() {
    String query = "SELECT MAX(order_id) AS latest_order_id FROM dbo.Orders";
    
    try (Connection conn = DBcontext.getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}
public void cancelOrder(int order_id) {
    String query = "UPDATE dbo.Orders SET status = 3 WHERE order_id = ?";

    try (Connection conn = DBcontext.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, order_id);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void updateOrder(Orders order) {
    String query = "UPDATE dbo.Orders SET fullname = ?, email = ?, phone_number = ?, address = ?, note = ? WHERE order_id = ?";

    try (Connection conn = DBcontext.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, order.getFullname());
        ps.setString(2, order.getEmail());
        ps.setString(3, order.getPhoneNumber());
        ps.setString(4, order.getAddress());
        ps.setString(5, order.getNote());
        ps.setInt(6, order.getOrderId());

        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void insertOrder(Orders order) {
    String query = "INSERT INTO dbo.Orders (account_id, fullname, email, phone_number, address, note, order_date, status, total_money) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBcontext.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, order.getAccountId());
        ps.setString(2, order.getFullname());
        ps.setString(3, order.getEmail());
        ps.setString(4, order.getPhoneNumber());
        ps.setString(5, order.getAddress());
        ps.setString(6, order.getNote());
        ps.setDate(7, (Date) order.getOrderDate());
        ps.setInt(8, order.getStatus());
        ps.setInt(9, order.getTotalMoney());

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void insertOrderDetail(int order_id, int product_id, int price, int amount) {
    String query = "INSERT INTO dbo.Orders_Details (order_id, product_id, price, amount) VALUES (?, ?, ?, ?)";

    try (Connection conn = DBcontext.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, order_id);
        ps.setInt(2, product_id);
        ps.setInt(3, price);
        ps.setInt(4, amount);

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}



    public static void main(String[] args) {
        // Tạo một đối tượng Orders để kiểm thử
        OrderDAO dao = new OrderDAO();
        dao.getOrder();
       
        System.out.println("Order inserted successfully!");
    }
}






