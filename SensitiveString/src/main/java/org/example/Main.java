package org.example;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        // Sử dụng cho PlainSensitiveString
        SensitiveString plainSensitive = SensitiveStringFactory.createSensitiveString("plain", "myPassword");
        System.out.println("Plain Sensitive String (getValue): " + new String(plainSensitive.getValue()));
        System.out.println("Display: " + plainSensitive.display());
        System.out.println();

        // Sử dụng cho MaskedSensitiveString
        SensitiveString maskedSensitive = SensitiveStringFactory.createSensitiveString("masked", "myPassword");

        System.out.println("Masked Sensitive String (getValue): " + new String(maskedSensitive.getValue()));
        System.out.println("Display: " + maskedSensitive.display());
        System.out.println();

        // Sử dụng cho EncryptedSensitiveString
        try {
            String algorithm = "AES/CBC/PKCS5Padding";
            byte[] key = "1234567890123456".getBytes(StandardCharsets.UTF_8);
            byte[] iv = "abcdefghijklmnop".getBytes(StandardCharsets.UTF_8);
            SensitiveString encryptedSensitive = SensitiveStringFactory.createSensitiveString("encrypted", "myPassword", algorithm, key, iv);
            System.out.println("Encrypted Sensitive String (getValue - decrypted): " + new String(encryptedSensitive.getValue()));
            System.out.println("Display: " + encryptedSensitive.display());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}