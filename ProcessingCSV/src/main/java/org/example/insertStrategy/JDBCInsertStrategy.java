package org.example.insertStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JDBCInsertStrategy implements InsertStrategy {
    private final Connection connection;

    public JDBCInsertStrategy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertData(List<String[]> batch) throws SQLException {
        String sql = "INSERT INTO Employee (id, name, email, salary, department) VALUES (?, ?, ?, ?, ?)";
        int batchSize = 100; // Giới hạn batch size

        // Tắt auto-commit để tối ưu hiệu suất
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int count = 0;

            for (String[] data : batch) {
                try {
                    System.out.println("📥 Đang insert: " + Arrays.toString(data));
                    // Kiểm tra dữ liệu trước khi insert
                    if (data.length < 5) {
                        System.err.println("Dữ liệu không hợp lệ: " + Arrays.toString(data));
                        continue;
                    }

                    stmt.setString(1, data[0]); // id
                    stmt.setString(2, data[1]); // name
                    stmt.setString(3, data[2]); // email
                    stmt.setFloat(4, Float.parseFloat(data[3].trim())); // salary
                    stmt.setString(5, data[4]); // department
                    stmt.addBatch();
                    count++;

                    // Chạy batch sau mỗi batchSize rows
                    if (count % batchSize == 0) {
                        stmt.executeBatch();
                        stmt.clearBatch();
                    }
                } catch (SQLException e) {
                    System.err.println("❌ Lỗi khi insert dòng: " + Arrays.toString(data));
                    e.printStackTrace();
                }
            }
            // Chạy phần còn lại trong batch
            stmt.executeBatch();
            connection.commit();
            System.out.println("✅ Insert batch thành công!");
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            // Phục hồi lại trạng thái auto-commit ban đầu
            connection.setAutoCommit(autoCommit);
        }
    }
}
