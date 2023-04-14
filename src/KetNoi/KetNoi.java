package KetNoi;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoi {

    public static Connection ketNoi(String database) {
        try {
            String user = "sa";
            String pass = "2502";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-G91BHG0\\SQLEXPRESS:1433;databaseName=" + database;
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
