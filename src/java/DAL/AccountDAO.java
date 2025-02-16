/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author Admin
 */
public class AccountDAO {

//    public Account login(String account_name, String password) {
//        String sql = "SELECT [account_id]\n"
//                + "      ,[role_id]\n"
//                + "      ,[fullname]\n"
//                + "      ,[email]\n"
//                + "      ,[phone_number]\n"
//                + "      ,[address]\n"
//                + "      ,[account_name]\n"
//                + "      ,[password]\n"
//                + "      ,[created_at]\n"
//                + "      ,[updated_at]\n"
//                + "      ,[deleted]\n"
//                + "      ,[date_of_birth]\n"
//                + "      ,[gender]\n"
//                + "      ,[avatar]\n"
//                + "  FROM [dbo].[Account]\n"
//                + "  where account_name =? and  password = ?";
//        try{
//            Connection conn = DBcontext.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            ps.setString(1, account_name);
//            ps.setString(2, password);
//            if(rs.next()){
//                Account a = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getTimestamp(9), rs.getTimestamp(10), rs.getInt(11), rs.getDate(12),
//                        rs.getInt(13), rs.getString(14));
//                return a;
//                }
//        }
//        catch(Exception e){
//            
//        }
//        return null;
//    }
       public Account login(String account_name, String password) {
        String sql = "select * from [dbo].[Account] where account_name  = ? and password  = ?";
        try {
            Connection conn = DBcontext.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, account_name); // Set parameters first
          ps.setString(2, password);
          ResultSet rs = ps.executeQuery();
         
            if (rs.next()) {
                 System.out.println("Found user with ID: " + rs.getInt(1));
            System.out.println("Role ID: " + rs.getInt(2));
                Account a = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getTimestamp(9),
                        rs.getTimestamp(10), rs.getInt(11), rs.getDate(12),
                        rs.getInt(13), rs.getString(14));
                return a;
            } else {
            System.out.println("No user found with provided credentials");
        }

        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
        
    }
       
          public Account checkUserExist(String account_name) {
        String query = "select * from [dbo].[Account] where account_name = ? ";
        try {
             Connection conn = DBcontext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, account_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getTimestamp(9),
                        rs.getTimestamp(10), rs.getInt(11), rs.getDate(12),
                        rs.getInt(13), rs.getString(14));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
    
    
    public List<Account> getAllAccount() {
        // lấy tất cả các bảng account trong DB
        //List<Account> chứa danh sách các tài khoản
        List<Account> accountList = new ArrayList<>();
        // tạo 1 dsach rỗng để lưu trữ các tài khoản lấy từ DB
        try {
            
            Connection conn = DBcontext.getConnection();
            String sql = "SELECT [account_id]\n"
                    + "      ,[role_id]\n"
                    + "      ,[fullname]\n"
                    + "      ,[email]\n"
                    + "      ,[phone_number]\n"
                    + "      ,[address]\n"
                    + "      ,[account_name]\n"
                    + "      ,[password]\n"
                    + "      ,[created_at]\n"
                    + "      ,[updated_at]\n"
                    + "      ,[deleted]\n"
                    + "      ,[date_of_birth]\n"
                    + "      ,[gender]\n"
                    + "      ,[avatar]\n"
                    + "  FROM [dbo].[Account]";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
// thực thi truy vấn và nhận kết quả trong RS
            while (rs.next()) {// duyệt từng dòng data trong RS 
                Account acc = new Account(rs.getInt("account_id"),
                        rs.getInt("role_id"), rs.getString("fullname"), rs.getString("email"),
                        rs.getString("phone_number"), rs.getString("address"), rs.getString("account_name"),
                        rs.getString("password"), null, null, rs.getInt("deleted"),
                        null, rs.getInt("gender"), rs.getString("avatar"));
                accountList.add(acc);
            }
            rs.close();// đóng kết nối result
            ps.close();// đóng preparedStatement 
            conn.close();// đóng connection
            // giải phóng tài nguyên
        } catch (Exception e) {
            e.printStackTrace();// nếu có lỗi, iin ra lỗi để bug
        }
        return accountList;
    }
    
    public void create(Account account) {
        String sql = "INSERT INTO [dbo].[Account] (fullname, email, account_name, password, role_id, created_at) "
                + "VALUES (?, ?, ?, ?, ?, GETDATE())";
        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, account.getFullname());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getAccount_name());
            ps.setString(4, account.getPassword());
            ps.setInt(5, account.getRole_id());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account created successfully.");
            } else {
                System.out.println("Failed to create account.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account findById(int id) {
        String sql = "SELECT * FROM [dbo].[Account] WHERE account_id = ?";
        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getInt("role_id"), rs.getString("fullname"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),
                        rs.getString("account_name"), rs.getString("password"), rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"), rs.getInt("deleted"), rs.getDate("date_of_birth"),
                        rs.getInt("gender"), rs.getString("avatar"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void update(Account account) {
        String sql = "UPDATE [dbo].[Account] SET fullname = ?, email = ?, phone_number = ?, address = ?, gender = ?, role_id = ? WHERE account_id = ?";
        try (Connection conn = DBcontext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account.getFullname());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPhone_number());
            ps.setString(4, account.getAddress());
            ps.setInt(5, account.getGender());
            ps.setInt(6, account.getRole_id());
            ps.setInt(7, account.getAccount_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void updateUser(Account user) {
        String query = "UPDATE [dbo].[Account] SET [fullname] = ?, [phone_number] = ? ,[email] = ? ,[address] = ?, [updated_at] = ? WHERE account_id = ?";
        try {
           Connection conn = DBcontext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getPhone_number());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(6, user.getAccount_id());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
        public void signupUser(Account user) {
    String insertUserQuery = "INSERT INTO Account (role_id, fullname, email, phone_number, address, account_name, password, created_at, updated_at, deleted, date_of_birth, gender, avatar) "
            + "VALUES (3, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?)";

    try (Connection conn = DBcontext.getConnection();
         PreparedStatement ps = conn.prepareStatement(insertUserQuery)) {

        ps.setString(1, user.getFullname());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPhone_number());
        ps.setString(4, user.getAddress());
        ps.setString(5, user.getAccount_name());
        ps.setString(6, user.getPassword());
        ps.setTimestamp(7, user.getCreated_at());
        ps.setTimestamp(8, user.getUpdated_at());
        ps.setDate(9, user.getDate_of_birth());
        ps.setInt(10, user.getGender());
        ps.setString(11, user.getAvatar()); 
        
        ps.executeUpdate();
        System.out.println("User successfully created with role_id = 3.");
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
//    public static void main(String[] args) {
//        Account a = new Account();
//        a.setAccount_id(7);
//        a.setFullname("Nguyễn Minh Quân");
//        a.setEmail("quan@gmail.com");
//        a.setPhone_number("0889501528");
////        a.setAccount_name("quan");
////        a.setPassword("123abc");
//        a.setAddress("Ninh Buồn");
//        a.setGender(0);
//        a.setRole_id(3);
//        AccountDAO accountDAO = new AccountDAO();
//        accountDAO.update(a);
////        accountDAO.create(a);
////            System.out.println(accountDAO.findById(2));
//    }
    

        }
     
}
