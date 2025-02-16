package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Suppliers;

public class SuppliersDAO extends DBcontext {

    public Suppliers getSupplierById(int supplierId) {
        Suppliers supplier = null;
        String sql = "SELECT * FROM Suppliers WHERE supplier_id = ?";

        try (Connection conn = DBcontext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, supplierId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    supplier = new Suppliers();
                    supplier.setSupplierId(rs.getInt("supplier_id"));
                    supplier.setName(rs.getString("name"));
                    supplier.setContactInfo(rs.getString("contact_info"));
                    supplier.setAddress(rs.getString("address"));
                    supplier.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }
    
    public List<Suppliers> getAllSupplier() {
        List<Suppliers> supplier = new ArrayList<>();
        try {
            java.sql.Connection conn = DBcontext.getConnection();
            String sql = "SELECT * FROM Suppliers";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Suppliers supply = new Suppliers();
                supply.setSupplierId(rs.getInt("supplier_id"));
                supply.setName(rs.getString("name"));
                supply.setContactInfo(rs.getString("contact_info")); 
                supply.setAddress(rs.getString("address"));
                supply.setCreatedAt(rs.getTimestamp("created_at"));
                supplier.add(supply);

            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }
    
    public List<Suppliers> getSuppliersByPagingAndNameContactAddress(int index, int pageSize, String name, String contact_info, String address) {

        List<Suppliers> suppliers = new LinkedList<>();
        String sql = "SELECT * FROM Suppliers WHERE name like ? and contact_info like ? and address like ? order by supplier_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + contact_info + "%");
            ps.setString(3, "%" + address + "%");
            ps.setInt(4, (index - 1) * pageSize);
            ps.setInt(5, pageSize);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Suppliers supplier = new Suppliers();
                    supplier.setSupplierId(rs.getInt("supplier_id"));
                    supplier.setName(rs.getString("name"));
                    supplier.setContactInfo(rs.getString("contact_info"));
                    supplier.setAddress(rs.getString("address"));
                    supplier.setCreatedAt(rs.getTimestamp("created_at"));
                    suppliers.add(supplier);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public int getTotalSuppliers() {

        String sql = "SELECT COUNT(*) FROM Suppliers";

        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addSupplier(Suppliers supplier) {
        String sql = "insert into suppliers ([name], [contact_info] ,[address], [created_at]  )  values (?, ?, ?, ?)";

        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int indexQuery = 1;
            ps.setString(indexQuery++, supplier.getName());
            ps.setString(indexQuery++, supplier.getContactInfo());
            ps.setString(indexQuery++, supplier.getAddress());
            ps.setTimestamp(indexQuery++, new Timestamp(supplier.getCreatedAt().getTime()));
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSupplier(Suppliers supplier) {
        String sql = "update suppliers set [name] = ?, [contact_info] = ? ,[address] = ? where supplier_id = ?";

        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int indexQuery = 1;
            ps.setString(indexQuery++, supplier.getName());
            ps.setString(indexQuery++, supplier.getContactInfo());
            ps.setString(indexQuery++, supplier.getAddress());
//            ps.setTimestamp(indexQuery++, supplier.getCreatedAt());
            ps.setInt(indexQuery++, supplier.getSupplierId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSupplier(int id) {
        String sql = "delete suppliers where supplier_id = ?";

        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int indexQuery = 1;
            ps.setInt(indexQuery++, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
