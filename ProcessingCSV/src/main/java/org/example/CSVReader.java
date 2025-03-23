package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {
    public static List<String[]> read(String filePath) {
        List<String[]> data = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("❌ File không tồn tại: " + filePath);
            return data; // Trả về danh sách rỗng
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            System.err.println("❌ Lỗi đọc file: " + e.getMessage());
        }

        return data;
    }


}
