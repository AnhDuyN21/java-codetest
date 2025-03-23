package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

class CSVReader {
    public static List<String[]> read(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines().map(line -> line.split(","))
                    .forEach(data::add);
        }
        return data;
    }
}
