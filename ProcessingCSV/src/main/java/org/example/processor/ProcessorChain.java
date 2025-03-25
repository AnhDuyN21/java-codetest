package org.example.processor;

import lombok.*;

import java.util.List;

public class ProcessorChain implements Processor {
    private final Processor processor;

    @Builder
    public ProcessorChain(@Singular List<Processor> processors) {
        if (processors == null || processors.isEmpty()) {
            throw new IllegalStateException("Không có Validator nào được thêm vào!");
        }

        for (int i = 0; i < processors.size() - 1; i++) {
            processors.get(i).setNext(processors.get(i + 1));
        }

        this.processor = processors.get(0);
    }


    @Override
    public Processor setNext(Processor next) {
        throw new UnsupportedOperationException("ProcessorChain không hỗ trợ setNext");
    }

    @Override
    public void process(String[] data) {
        if (processor != null) {
            processor.process(data);
        }
    }
}

