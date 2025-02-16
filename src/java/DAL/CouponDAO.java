package DAL;

import model.Coupon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO extends DBcontext {
    public Coupon getCouponByCode(String code) {
        String sql = "SELECT * FROM Coupons WHERE code = ? AND expiration_date >= GETDATE() AND usage_limit > 0";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Coupon(
                    rs.getInt("coupon_id"),
                    rs.getString("code"),
                    rs.getInt("discount_percentage"),
                    rs.getInt("max_discount_amount"),
                    rs.getDate("expiration_date"),
                    rs.getInt("usage_limit"),
                    rs.getBoolean("applicable_to_all_categories"),
                    rs.getInt("created_by")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCouponUsage(Coupon coupon) {
      String sql = "UPDATE Coupons SET usage_limit = ? WHERE coupon_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, coupon.getUsageLimit());
              st.setInt(2, coupon.getCouponId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Coupon getCouponById(int couponId) {
    String sql = "SELECT * FROM Coupons WHERE coupon_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, couponId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Coupon(
                rs.getInt("coupon_id"),
                rs.getString("code"),
                rs.getInt("discount_percentage"),
                rs.getInt("max_discount_amount"),
                rs.getDate("expiration_date"),
                rs.getInt("usage_limit"),
                rs.getBoolean("applicable_to_all_categories"),
                rs.getInt("created_by")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
    public boolean addCoupon(Coupon coupon) {
    String sql = "INSERT INTO Coupons (code, discount_percentage, max_discount_amount, expiration_date, usage_limit, applicable_to_all_categories, created_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, coupon.getCode());
        st.setInt(2, coupon.getDiscountPercentage());
        st.setInt(3, coupon.getMaxDiscountAmount());
        st.setDate(4, new java.sql.Date(coupon.getExpirationDate().getTime()));
        st.setInt(5, coupon.getUsageLimit());
        st.setBoolean(6, coupon.isApplicableToAllCategories());
        st.setInt(7, coupon.getCreatedBy());
        st.executeUpdate();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean updateCoupon(Coupon coupon) {
    String sql = "UPDATE Coupons SET code = ?, discount_percentage = ?, max_discount_amount = ?, expiration_date = ?, usage_limit = ?, applicable_to_all_categories = ?, created_by = ? WHERE coupon_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, coupon.getCode());
        st.setInt(2, coupon.getDiscountPercentage());
        st.setInt(3, coupon.getMaxDiscountAmount());
        st.setDate(4, new java.sql.Date(coupon.getExpirationDate().getTime()));
        st.setInt(5, coupon.getUsageLimit());
        st.setBoolean(6, coupon.isApplicableToAllCategories());
        st.setInt(7, coupon.getCreatedBy());
        st.setInt(8, coupon.getCouponId());
        st.executeUpdate();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean deleteCoupon(int couponId) {
    String sql = "DELETE FROM Coupons WHERE coupon_id = ?";
    try (Connection conn = getConnection();
         PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, couponId);
        st.executeUpdate();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    public List<Coupon> getAllCoupons() {
    List<Coupon> coupons = new ArrayList<>();
    String sql = "SELECT * FROM Coupons";
    try (Connection conn = getConnection();
         PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            Coupon coupon = new Coupon(
                rs.getInt("coupon_id"),
                rs.getString("code"),
                rs.getInt("discount_percentage"),
                rs.getInt("max_discount_amount"),
                rs.getDate("expiration_date"),
                rs.getInt("usage_limit"),
                rs.getBoolean("applicable_to_all_categories"),
                rs.getInt("created_by")
            );
            coupons.add(coupon);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return coupons;
}
}