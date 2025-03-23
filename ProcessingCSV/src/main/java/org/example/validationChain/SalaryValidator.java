package org.example.validationChain;

import org.example.Employee;

public class SalaryValidator extends BaseValidator {
    @Override
    public boolean validate(String[] data) {
        try {
            int salary = Integer.parseInt(data[3].trim());
            return salary >= 0 && super.validate(data);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
