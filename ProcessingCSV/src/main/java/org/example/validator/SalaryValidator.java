package org.example.validator;

public class SalaryValidator extends BaseValidator {
    @Override
    public boolean validate(String[] data) {
        try {
            float salary = Float.parseFloat(data[3].trim());
            return salary >= 0 && super.validate(data);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
