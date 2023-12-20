package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
    protected static Connection conn;
    protected static Statement stmt;
    protected static ResultSet rs;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/negocioventas?user=root&password=admin");
            stmt = conn.createStatement();
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }

    protected DBConn() {
        
    }

    public static void closeConnection() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
        finally {
            System.out.println("\nConexión terminada...");
        }
    }
}