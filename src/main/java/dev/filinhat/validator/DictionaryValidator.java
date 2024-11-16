package dev.filinhat.validator;

/**
 * Интерфейс валидатора ввода пользователем
 */
public interface DictionaryValidator {

    /**
     * Проверяет валидность ключа
     *
     * @param key ключ для проверки
     * @return true, если ключ валиден, иначе false
     */
    boolean isValidKey(String key);
}
