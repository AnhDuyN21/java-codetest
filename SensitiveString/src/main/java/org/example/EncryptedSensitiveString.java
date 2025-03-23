package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncryptedSensitiveString implements SensitiveString {

    private final byte[] encryptedValue;
    private final String algorithm;
    private final SecretKeySpec secretKey;
    private final IvParameterSpec ivSpec;

    public EncryptedSensitiveString(String original, String algorithm, byte[] key, byte[] iv) throws Exception {
        this.algorithm = algorithm;
        this.secretKey = new SecretKeySpec(key, "AES");
        this.ivSpec = new IvParameterSpec(iv);
        this.encryptedValue = encrypt(original);
    }

    private byte[] encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    private String decrypt() throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        return new String(cipher.doFinal(encryptedValue), StandardCharsets.UTF_8);
    }

    @Override
    public char[] getValue() {
        try {
            return decrypt().toCharArray();
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }

    @Override
    public String display() {
        return "*".repeat(encryptedValue.length);
    }

    @Override
    public void clear() {
        Arrays.fill(encryptedValue, (byte) 0);
    }
}
