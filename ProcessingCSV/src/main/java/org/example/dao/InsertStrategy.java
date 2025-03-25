package org.example.dao;

import java.sql.SQLException;
import java.util.List;

public interface InsertStrategy {
    void insertData(List<String[]> batch) throws SQLException;
}
