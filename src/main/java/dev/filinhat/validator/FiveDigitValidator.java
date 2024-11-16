package dev.filinhat.validator;

import org.springframework.stereotype.Component;

/**
 * Валидатор для ключей из 5 цифр
 */
@Component
public class FiveDigitValidator implements DictionaryValidator {
    @Override
    public boolean isValidKey(String key) {
        return key.length() == 5 && key.matches("[0-9]+");
    }
}
