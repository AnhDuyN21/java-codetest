package org.example;

public interface ISensitiveString {
    char[] maskString(String sensitiveString);
    char[] encodingString(String sensitiveString);
    String decodingString(String sensitiveString);
}
