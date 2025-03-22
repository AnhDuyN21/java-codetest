package org.example.validationChain;

import org.example.Employee;

abstract class BaseValidator implements Validator {
    protected Validator next;

    @Override
    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    @Override
    public boolean validate(String[] data)
    {
        return next == null || next.validate(data);
    }
}