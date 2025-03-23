package org.example.validationChain;


import java.util.regex.Pattern;

public class EmailValidator extends BaseValidator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Override
    public boolean validate(String[] data) {
        String email = data[2].trim();
        boolean isEmailValid = EMAIL_PATTERN.matcher(email).matches();
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }
        return super.validate(data);
    }
}
