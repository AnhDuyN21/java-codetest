package org.example.processingChain;

import org.example.Employee;

public class TrimProcessor extends BaseProcessor {
    @Override
    public void process(String[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
        }
        super.process(data);
    }
}
