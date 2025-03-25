package org.example.validator;

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
        if (next == null) {
            return true;
        } else {
            return next.validate(data);
        }
    }
}