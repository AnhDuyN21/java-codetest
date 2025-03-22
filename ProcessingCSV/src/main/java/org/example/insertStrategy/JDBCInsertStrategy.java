package org.example.insertStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JDBCInsertStrategy implements InsertStrategy {
    private Connection connection;

    public JDBCInsertStrategy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertData(List<String[]> batch) throws SQLException {
        String sql = "INSERT INTO Employee (id, name, email, salary, department) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int batchSize = 100; // Chia nhỏ batch để tránh quá tải
            int count = 0;

            for (String[] data : batch) {
                try {
                    stmt.setString(1, data[0]); // id
                    stmt.setString(2, data[1]); // name
                    stmt.setString(3, data[2]); // email
                    stmt.setInt(4, Integer.parseInt(data[3].trim())); // salary
                    stmt.setString(5, data[4]); // department
                    stmt.addBatch();

                    count++;

                    // Chạy batch sau mỗi batchSize rows để tránh tràn bộ nhớ
                    if (count % batchSize == 0) {
                        stmt.executeBatch();
                        stmt.clearBatch();
                    }
                } catch (SQLException e) {
                    System.err.println("❌ Lỗi khi insert dòng: " + Arrays.toString(data));
                    e.printStackTrace();
                }
            }
            // Chạy nốt phần còn lại trong batch
            stmt.executeBatch();
            connection.commit(); // Đảm bảo dữ liệu được lưu lại
            System.out.println("✅ Insert batch thành công!");
        } catch (SQLException e) {
            connection.rollback(); // Rollback nếu có lỗi lớn
            throw e;
        }
    }
}
