package DAL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcontext {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=hungdepzai";
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
