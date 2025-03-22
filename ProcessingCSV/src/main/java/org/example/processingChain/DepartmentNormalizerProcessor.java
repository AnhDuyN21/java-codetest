package org.example.processingChain;

public class DepartmentNormalizerProcessor extends BaseProcessor {
    @Override
    public void process(String[] data) {
        if (data.length > 4) {
            data[4] = data[4].toUpperCase();
        }
        super.process(data);
    }
}
