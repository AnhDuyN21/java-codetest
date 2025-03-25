package org.example.validator;

public interface Validator {
    Validator setNext(Validator next);
    boolean validate(String[] data);
}
