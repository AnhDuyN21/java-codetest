package org.example.processingChain;

import org.example.Employee;

public interface Processor {
    Processor setNext(Processor next);
    void process(String[] data);
}
