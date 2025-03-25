package org.example.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBConnectionUtil {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeManagements;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10); // Giới hạn số kết nối tối đa
        config.setMinimumIdle(2); // Số kết nối tối thiểu giữ trong pool
        config.setIdleTimeout(30000); // 30 giây timeout khi idle
        config.setMaxLifetime(600000); // 10 phút là tối đa cho một connection
        config.setConnectionTimeout(3000); // Timeout khi lấy connection

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}

