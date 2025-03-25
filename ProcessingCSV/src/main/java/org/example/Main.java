package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.dao.InsertStrategy;
import org.example.dao.JDBCInsertStrategy;
import org.example.processor.*;
import org.example.util.DBConnectionUtil;
import org.example.validator.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        DataSource dataSource = DBConnectionUtil.getDataSource();

        try (Connection connection = dataSource.getConnection()) {
            InsertStrategy strategy = new JDBCInsertStrategy(connection);

            // Khởi tạo Validators
            RequiredFieldsValidator requiredValidator = new RequiredFieldsValidator();
            EmailValidator emailValidator = new EmailValidator();
            SalaryValidator salaryValidator = new SalaryValidator();

            // Khởi tạo Processors
            TrimProcessor trimProcessor = new TrimProcessor();
            NameFormatterProcessor nameFormatterProcessor = new NameFormatterProcessor();
            DepartmentNormalizerProcessor departmentNormalizerProcessor = new DepartmentNormalizerProcessor();

            // Xây dựng chuỗi validator
            Validator validatorChain = ValidatorChain.builder()
                    .validator(requiredValidator)
                    .validator(emailValidator)
                    .validator(salaryValidator)
                    .build();

            // Xây dựng chuỗi processor
            Processor processingChain = ProcessorChain.builder()
                    .processor(trimProcessor)
                    .processor(nameFormatterProcessor)
                    .processor(departmentNormalizerProcessor)
                    .build();

            // Đọc dữ liệu từ file CSV
            List<String[]> employees = CSVReader.read("D:/Trash/employees_sample.csv");

            // Thực hiện nhập dữ liệu vào DB
            DataImporter importer = new DataImporter(validatorChain, processingChain, strategy);
            importer.importData(employees);

            System.out.println("✅ Nhập dữ liệu thành công!");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối database!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xử lý dữ liệu!");
            e.printStackTrace();
        }
    }
}