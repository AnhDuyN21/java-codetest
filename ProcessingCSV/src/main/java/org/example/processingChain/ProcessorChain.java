package org.example.processingChain;

import org.example.validationChain.Validator;
import org.example.validationChain.ValidatorChain;

import java.util.ArrayList;
import java.util.List;

public final class ProcessorChain {
    private final Processor processor;

    private ProcessorChain(Processor processor) {
        this.processor = processor;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private final List<Processor> processors = new ArrayList<>();

        public ProcessorChain.Builder add(Processor processor) {
            processors.add(processor);
            return this;
        }

        public Processor build() {
            if (processors.isEmpty()) {
                throw new IllegalStateException("Không có Processor nào được thêm vào!");
            }

            for (int i = 0; i < processors.size() - 1; i++) {
                processors.get(i).setNext(processors.get(i + 1));
            }

            return processors.get(0);
        }
    }
}
