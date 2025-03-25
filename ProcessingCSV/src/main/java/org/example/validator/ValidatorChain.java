package org.example.validator;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
public class ValidatorChain implements Validator {
    private final Validator validator;

    @Builder
    public ValidatorChain(@Singular List<Validator> validators) {
        if (validators == null || validators.isEmpty()) {
            throw new IllegalStateException("Không có Validator nào được thêm vào!");
        }

        // Thiết lập chuỗi Validator
        for (int i = 0; i < validators.size() - 1; i++) {
            validators.get(i).setNext(validators.get(i + 1));
        }

        this.validator = validators.get(0);
    }

    @Override
    public Validator setNext(Validator next) {
        throw new UnsupportedOperationException("ValidatorChain không hỗ trợ setNext");
    }

    @Override
    public boolean validate(String[] data) {
        return validator != null && validator.validate(data);
    }
}
