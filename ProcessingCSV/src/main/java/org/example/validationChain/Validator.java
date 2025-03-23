package org.example.validationChain;

import org.example.Employee;

public interface Validator {
    Validator setNext(Validator next);
    boolean validate(String[] data);
}
