package org.example;

public class Main {
    static ISensitiveString sensitiveString = new SensitiveString();
    public static void main(String[] args) {

        System.out.println(sensitiveString.maskString("aaabbb"));
        System.out.println(sensitiveString.encodingString("aaabbb"));
        System.out.println(sensitiveString.decodingString("YWFhYmJi"));
    }
}