package org.example;

public class MaskedSensitiveString extends SensitiveStringDecorator{


    public MaskedSensitiveString(ISensitiveString wrapped) {
        super(wrapped);
    }

    @Override
    public String display() {
        return "****" + new String(wrapped.getValue()).substring(Math.max(0, wrapped.getValue().length - 4));
    }

}
