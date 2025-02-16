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
        // lấy tất cả các bảng account trong DB
        //List<Account> chứa danh sách các tài khoản
        List<Role> roleList = new ArrayList<>();
        // tạo 1 dsach rỗng để lưu trữ các tài khoản lấy từ DB
        try {

            Connection conn = DBcontext.getConnection();
            String sql = "select * from Role";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
// thực thi truy vấn và nhận kết quả trong RS
            while (rs.next()) {// duyệt từng dòng data trong RS 
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                roleList.add(role);
            }
            rs.close();// đóng kết nối result
            ps.close();// đóng preparedStatement 
            conn.close();// đóng connection
            // giải phóng tài nguyên
        } catch (Exception e) {
            e.printStackTrace();// nếu có lỗi, iin ra lỗi để bug
        }
        return roleList;
    }
//    public static void main(String[] args) {
//        RoleDAO r = new RoleDAO();
//        System.out.println(r.getAllAccount());
//    }
}
