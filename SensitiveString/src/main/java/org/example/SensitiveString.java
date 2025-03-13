package org.example;

import java.util.Arrays;
import java.util.Base64;

public class SensitiveString implements ISensitiveString {

    @Override
    public char[] maskString(String sensitiveInformation) {
        char [] arr = sensitiveInformation.toCharArray();
        Arrays.fill(arr, '*');
        return arr;
    }

    @Override
    public char[] encodingString(String sensitiveString) {
        char[] encodedString = Base64.getEncoder().encodeToString(sensitiveString.getBytes()).toCharArray();
        System.out.println(encodedString);
        return encodedString;
    }

    @Override
    public String decodingString(String sensitiveString) {
        byte [] decodedString = Base64.getDecoder().decode(sensitiveString);
        return new String(decodedString);
    }
}
