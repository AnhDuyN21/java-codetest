package org.example.processingChain;

public class NameFormatterProcessor extends BaseProcessor {
    @Override
    public void process(String[] data) {
        if (data.length > 1 && !data[1].isEmpty()) {
            data[1] = data[1].substring(0, 1).toUpperCase() + data[1].substring(1).toLowerCase();
        }
        super.process(data);
    }
}
