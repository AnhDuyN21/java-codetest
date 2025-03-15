package org.example;

public class EncryptedSensitiveString extends SensitiveStringDecorator{

    public EncryptedSensitiveString(ISensitiveString wrapped) {
        super(wrapped);
    }

    @Override
    public String display() {
        return "[ENCRYPTED]";
    }
}
