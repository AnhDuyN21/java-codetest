package org.example;


import java.util.Arrays;

public class PlainSensitiveString implements  ISensitiveString{
    private final char[] value;

    public PlainSensitiveString(String value) {
        this.value = value.toCharArray();
    }

    @Override
    public char[] getValue() {
        return value;
    }

    @Override
    public String display() {
        return new String(value);
    }

    @Override
    public void clear() {
        Arrays.fill(value, '\0');
    }
}
