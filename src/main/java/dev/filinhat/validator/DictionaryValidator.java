package dev.filinhat.validator;

/**
 * Интерфейс валидатора ввода пользователем
 */
public interface DictionaryValidator {
    boolean isValidKey(String key);
}
