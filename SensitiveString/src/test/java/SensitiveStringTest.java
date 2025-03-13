

import org.example.SensitiveString;
import org.junit.Test;

import java.util.Arrays;
import java.util.Base64;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SensitiveStringTest {
    private final SensitiveString sensitiveString = new SensitiveString();

    @Test
    public void testMaskString() {
        String input = "matkhausieucap";
        char[] masked = sensitiveString.maskString(input);
        char[] expected = new char[input.length()];
        Arrays.fill(expected, '*');
        assertArrayEquals(expected, masked);
    }

    @Test
    public void testEncodingString() {
        String input = "Secret123";
        char[] encoded = sensitiveString.encodingString(input);
        System.out.println(encoded);
        String expected = Base64.getEncoder().encodeToString(input.getBytes());
        assertEquals(expected, new String(encoded));
    }

    @Test
    public void testDecodingString() {
        String original = "Secret123";
        String encoded = Base64.getEncoder().encodeToString(original.getBytes());
        String decoded = sensitiveString.decodingString(encoded);
        assertEquals(original, decoded);
    }
}

