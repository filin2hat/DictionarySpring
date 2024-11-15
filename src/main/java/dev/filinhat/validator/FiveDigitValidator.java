package dev.filinhat.validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Валидатор для ключей из 5 цифр
 */
@Component
@Qualifier("fiveDigitValidator")
public class FiveDigitValidator implements DictionaryValidator {
    @Override
    public boolean isValidKey(String key) {
        return key.length() == 5 && key.matches("[0-9]+");
    }
}
