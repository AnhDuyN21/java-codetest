package org.example.processingChain;

import org.example.Employee;

abstract class BaseProcessor implements Processor {
    protected Processor next;

    @Override
    public Processor setNext(Processor next) {
        this.next = next;
        return next;
    }

    @Override
    public void process(String[] data) {
        if (next != null) {
            next.process(data);
        }
    }
}
