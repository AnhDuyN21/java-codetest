package org.example.application.validator;


import org.example.validator.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorChainTest {

    private Validator validatorChain;

    @BeforeEach
    void setUp() {
        validatorChain = ValidatorChain.builder()
                .validator(new RequiredFieldsValidator())
                .validator(new EmailValidator())
                .validator(new SalaryValidator())
                .build();
    }

    // 🔹 Test email không hợp lệ
    @Test
    void testInvalidEmail() {
        String[] data = {"1", "John Doe", "invalid-email", "50000.0", "SALES"};
        assertFalse(validatorChain.validate(data), "Email không hợp lệ nhưng vẫn được chấp nhận");
    }

    // 🔹 Test lương âm
    @Test
    void testInvalidSalary() {
        String[] data = {"2", "Jane Doe", "jane@example.com", "-1000.0", "HR"};
        assertFalse(validatorChain.validate(data), "Lương âm nhưng vẫn được chấp nhận");
    }

    // 🔹 Test dòng trống
    @Test
    void testEmptyLine() {
        String[] data = {"", "", "", "", ""};
        assertFalse(validatorChain.validate(data), "Dòng trống nhưng vẫn được chấp nhận");
    }

    // 🔹 Test thiếu bất kỳ trường nào
    @Test
    void testMissingField() {
        String[] data = {"3", "Alice", "", "45000.0", "HR"}; // Thiếu email
        assertFalse(validatorChain.validate(data), "Thiếu email nhưng vẫn được chấp nhận");
    }

    // 🔹 Test dữ liệu hợp lệ
    @Test
    void testValidData() {
        String[] data = {"5", "Alice Johnson", "alice@example.com", "55000.0", "HR"};
        assertTrue(validatorChain.validate(data), "Dữ liệu hợp lệ nhưng bị từ chối");
    }
}
