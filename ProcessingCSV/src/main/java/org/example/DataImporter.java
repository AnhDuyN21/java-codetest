package org.example;

import org.example.dao.InsertStrategy;
import org.example.processor.Processor;
import org.example.validator.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DataImporter {
    private Validator validator;
    private Processor processor;
    private InsertStrategy strategy;

    public DataImporter(Validator validator, Processor processor, InsertStrategy strategy) {
        this.validator = validator;
        this.processor = processor;
        this.strategy = strategy;
    }

    public void importData(List<String[]> employees) throws SQLException {
        final int BATCH_SIZE = 100;
        List<String[]> validData = new ArrayList<>();
        for (String[] data : employees) {
            if (validator.validate(data)) {
                processor.process(data);
                validData.add(data);
                if (validData.size() == BATCH_SIZE) {
                    strategy.insertData(validData);
                    validData.clear();
                }
            }
        }
        if (!validData.isEmpty()) {
            strategy.insertData(validData);
            validData.clear();
        }
    }
}
