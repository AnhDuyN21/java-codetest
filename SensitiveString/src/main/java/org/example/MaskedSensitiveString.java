package org.example;

import java.util.Arrays;

public class MaskedSensitiveString implements SensitiveString {

    private final SensitiveString sensitiveString;

    public MaskedSensitiveString(SensitiveString sensitiveString) {
        this.sensitiveString = sensitiveString;
    }

    @Override
    public char[] getValue() {
        return new char[0];
    }

    @Override
    public String display() {
        int len = sensitiveString.getValue().length;
        char[] mask = new char[len];
        Arrays.fill(mask, '*');
        return new String(mask);
    }

    @Override
    public void clear() {
        sensitiveString.clear();
    }
}
