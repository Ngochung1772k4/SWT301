/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author ASUS
 */
public class RoleDAO extends DBcontext{

    public List<Role> getAllRole() {
        List<Role> roleList = new ArrayList<>();
        String sql = "SELECT * FROM Role";

        try (Connection conn = DBcontext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                roleList.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return roleList;
    }
}


