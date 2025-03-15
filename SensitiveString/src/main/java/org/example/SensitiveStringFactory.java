package org.example;

public class SensitiveStringFactory {
    public static ISensitiveString createSensitiveString(String value) {
        return new EncryptedSensitiveString(new MaskedSensitiveString(new PlainSensitiveString(value)));
    }
}
