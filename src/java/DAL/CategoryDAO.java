/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;

/**
 *
 * @author ngoch
 */
public class CategoryDAO extends DBcontext {

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            java.sql.Connection conn = DBcontext.getConnection();
            String sql = "SELECT * FROM Category where deleted = 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setCreatedAt(rs.getTimestamp("created_at"));
                category.setDeleted(rs.getInt("deleted"));
                categories.add(category);

            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryById(int categoryId) {
        Category category = null;
        try {

            java.sql.Connection conn = DBcontext.getConnection();
            String sql = "SELECT * FROM Category WHERE category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setCreatedAt(rs.getTimestamp("created_at"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public void insertCategory(Category category) {
        String query = "INSERT INTO [dbo].[Category] ([name],[description],[created_at])\n"
                + "     VALUES (?,?,?)";
        try {
            java.sql.Connection conn = DBcontext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setTimestamp(3, category.getCreatedAt());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateCategory(Category category) {
        String sql = "UPDATE [dbo].[Category] SET [name] = ?,[description] = ?,[created_at] = ?\n"
                + " WHERE category_id = ?";
        try {
            java.sql.Connection conn = DBcontext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setTimestamp(3, category.getCreatedAt());
            ps.setInt(4, category.getCategoryId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCategory(int category_id) {
        String query = "DELETE FROM [dbo].[Category] WHERE category_id = ?";
        try {
            java.sql.Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, category_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deletedCategory(int categoryId) {
        String query = "UPDATE Category SET deleted = 1 WHERE category_id = ?";
        try (java.sql.Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        dao.getAllCategories();
    }
}
