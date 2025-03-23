package org.example.validationChain;

import java.util.ArrayList;
import java.util.List;

public class ValidatorChain {
    private final Validator validator;

    private ValidatorChain(Validator validator) {
        this.validator = validator;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Sửa đổi để trả về Validator thay vì ValidatorChain
    public static class Builder {
        private final List<Validator> validators = new ArrayList<>();

        public Builder add(Validator validator) {
            validators.add(validator);
            return this;
        }

        public Validator build() {
            if (validators.isEmpty()) {
                throw new IllegalStateException("Không có Validator nào được thêm vào!");
            }

            for (int i = 0; i < validators.size() - 1; i++) {
                validators.get(i).setNext(validators.get(i + 1));
            }

            return validators.get(0);
        }
    }
}

