package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManagements;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        try {
            // Nạp driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy JDBC Driver của SQL Server.");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
