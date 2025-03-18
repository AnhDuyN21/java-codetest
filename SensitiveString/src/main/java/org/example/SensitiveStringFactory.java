package org.example;

public class SensitiveStringFactory {
    public static SensitiveString createSensitiveString(String type, String value) {
        return switch (type.toLowerCase()) {
            case "plain" -> new PlainSensitiveString(value);
            case "masked" -> new MaskedSensitiveString(new PlainSensitiveString(value));
            default -> throw new IllegalArgumentException("Invalid SensitiveString type: " + type);
        };
    }

    public static SensitiveString createSensitiveString(String type, String value,
                                                        String algorithm, byte[] key, byte[] iv) throws Exception {
        if (type.toLowerCase().equals("encrypted")) {
            return new EncryptedSensitiveString(value, algorithm, key, iv);
        }
        return createSensitiveString(type, value);
    }
}

