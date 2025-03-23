package org.example;


import lombok.val;
import org.example.insertStrategy.InsertStrategy;
import org.example.insertStrategy.JDBCInsertStrategy;
import org.example.processingChain.*;
import org.example.util.DBConnectionUtil;
import org.example.validationChain.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Connection connection = DBConnectionUtil.getConnection()) {
            InsertStrategy strategy = new JDBCInsertStrategy(connection);

            val requiredValidator = new RequiredFieldsValidator();
            val emailValidator = new EmailValidator();
            val salaryValidator = new SalaryValidator();

            val trimProcessor = new TrimProcessor();
            val nameFormatterProcessor = new NameFormatterProcessor();
            val departmentNormalizerProcessor = new DepartmentNormalizerProcessor();

            // Xây dựng chuỗi validator
            Validator validatorChain = ValidatorChain.builder()
                    .add(requiredValidator)
                    .add(emailValidator)
                    .add(salaryValidator)
                    .build();


            // Xây dựng chuỗi processor
            Processor processingChain = ProcessorChain.builder()
                    .add(trimProcessor)
                    .add(nameFormatterProcessor)
                    .add(departmentNormalizerProcessor)
                    .build();

            // Đọc dữ liệu từ file CSV
            List<String[]> employees = CSVReader.read("D:/Trash/employees_sample.csv");

            // Thực hiện nhập dữ liệu vào DB
            DataImporter importer = new DataImporter(validatorChain, processingChain, strategy);
            importer.importData(employees);

            System.out.println("Nhập dữ liệu thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}