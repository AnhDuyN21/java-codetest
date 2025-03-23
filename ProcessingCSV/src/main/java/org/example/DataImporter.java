package org.example;

import org.example.insertStrategy.InsertStrategy;
import org.example.processingChain.Processor;
import org.example.validationChain.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String[]> validData = new ArrayList<>();

        for (String[] data : employees) {
            boolean test = validator.validate(data);
            if (test) {
                processor.process(data);
                validData.add(data);
            }
        }

        strategy.insertData(validData);
    }
}
