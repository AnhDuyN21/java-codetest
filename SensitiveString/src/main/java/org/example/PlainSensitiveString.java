package org.example;


import java.util.Arrays;

public class PlainSensitiveString implements SensitiveString {
    private final char[] value;

    public PlainSensitiveString(String value) {

        this.value = value.toCharArray();
    }

    @Override
    public char[] getValue() {
        return Arrays.copyOf(value, value.length);
    }

    @Override
    public String display() {
        char[] mask = new char[value.length];
        Arrays.fill(mask, '*');
        return new String(mask);
    }

    @Override
    public void clear() {
        Arrays.fill(value, '\0');
    }
}
