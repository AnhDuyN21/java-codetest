package org.example;


import org.example.insertStrategy.InsertStrategy;
import org.example.insertStrategy.JDBCInsertStrategy;
import org.example.processingChain.DepartmentNormalizerProcessor;
import org.example.processingChain.NameFormatterProcessor;
import org.example.processingChain.Processor;
import org.example.processingChain.TrimProcessor;
import org.example.util.DBConnectionUtil;
import org.example.validationChain.EmailValidator;

import org.example.validationChain.RequiredFieldsValidator;
import org.example.validationChain.SalaryValidator;
import org.example.validationChain.Validator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Connection connection = DBConnectionUtil.getConnection()) {
            InsertStrategy strategy = new JDBCInsertStrategy(connection);

            // Xây dựng chuỗi validator
            Validator validationChain = new RequiredFieldsValidator();
            validationChain.setNext(new EmailValidator())
                    .setNext(new SalaryValidator());

            // Xây dựng chuỗi processor
            Processor processingChain = new TrimProcessor();
            processingChain.setNext(new NameFormatterProcessor())
                    .setNext(new DepartmentNormalizerProcessor());

            // Đọc dữ liệu từ file CSV
            List<String[]> employees = CSVReader.read("D:/Trash/employees_sample.csv");

            // Thực hiện nhập dữ liệu vào DB
            DataImporter importer = new DataImporter(validationChain, processingChain, strategy);
            importer.importData(employees);

            System.out.println("Nhập dữ liệu thành công!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}