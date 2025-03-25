package org.example.processor;

import java.util.regex.Pattern;

public class NameFormatterProcessor extends BaseProcessor {
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z\\s]");

    @Override
    public void process(String[] data) {
        if (data.length > 1 && !data[1].isEmpty()) {
            // Kiểm tra nếu tên chứa ký tự đặc biệt
            if (SPECIAL_CHAR_PATTERN.matcher(data[1]).find()) {
                return;
            }
            // Định dạng lại tên (chữ cái đầu viết hoa, các chữ còn lại viết thường)
            data[1] = data[1].substring(0, 1).toUpperCase() + data[1].substring(1).toLowerCase();
        }
        super.process(data);
    }
}
