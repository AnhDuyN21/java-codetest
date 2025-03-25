package org.example.application.processor;

import org.example.processor.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessorChainTest {
    private Processor procesorChain;

    @BeforeEach
    void setUp() {
        procesorChain = ProcessorChain.builder()
                .processor(new TrimProcessor())
                .processor(new NameFormatterProcessor())
                .processor(new DepartmentNormalizerProcessor())
                .build();
    }

    @Test
    void testProcessingChain() {
        String[] data = {" 123 ", "john doe ", "abc@gmail.com", "22020.22", " sales "};
        procesorChain.process(data);
        assertEquals("123", data[0]);
        assertEquals("John doe", data[1]);
        assertEquals("SALES", data[4]);
    }

    @Test
    void testNameContainsSpecialCharacters() {
        String[] data = {"4", "John@Doe", "john@example.com", "60000.0", "sales"};
        procesorChain.process(data);
        assertEquals("John@Doe", data[1]);
    }
}
