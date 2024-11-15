package dev.filinhat.service;

import java.util.Map;

/**
 * Интерфейс сервиса словаря.
 */
public interface DictionaryService {

    /**
     * Возвращает все записи из словаря.
     */
    Map<String, String> readEntries();

    /**
     * Добавляет новую запись в словарь.
     *
     * @param key   ключ записи.
     * @param value значение записи.
     * @throws IllegalArgumentException если ключ не соответствует требованиям валидатора.
     */
    void addEntry(String key, String value);

    /**
     * Удаляет запись из словаря по ключу.
     *
     * @param key ключ записи для удаления.
     */
    void deleteEntry(String key);

    /**
     * Ищет запись по ключу.
     *
     * @param key ключ записи.
     * @return значение записи или null, если запись не найдена.
     */
    String searchEntry(String key);
}
