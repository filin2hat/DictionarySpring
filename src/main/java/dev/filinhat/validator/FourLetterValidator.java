package dev.filinhat.validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Класс валидатора для ключей из 4 латинских букв
 */
@Component
@Qualifier("fourLetterValidator")
public class FourLetterValidator implements DictionaryValidator {
    @Override
    public boolean isValidKey(String key) {
        return key.length() == 4 && key.matches("[a-zA-Z]+");
    }
}
