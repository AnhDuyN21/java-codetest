package org.example;

abstract class SensitiveStringDecorator implements ISensitiveString {
    protected ISensitiveString wrapped;

    public SensitiveStringDecorator(ISensitiveString wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public char[] getValue() {
        return wrapped.getValue();
    }

    @Override
    public void clear() {
        wrapped.clear();
    }
}
