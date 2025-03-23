package org.example.validationChain;

import org.example.Employee;

public class RequiredFieldsValidator extends BaseValidator {
    @Override
    public boolean validate(String[] data) {

        if (data.length != 5) {
            return false;
        }

        for (String field : data) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }
        return super.validate(data);
    }
}
