package org.example.insertStrategy;

import org.example.Employee;

import java.sql.SQLException;
import java.util.List;

public interface InsertStrategy {
    void insertData(List<String[]> batch) throws SQLException;
}
