package org.example.processor;

public interface Processor {
    Processor setNext(Processor next);
    void process(String[] data);
}
