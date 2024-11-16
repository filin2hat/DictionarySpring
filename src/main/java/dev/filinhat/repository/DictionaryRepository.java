package dev.filinhat.repository;

import java.util.Map;

/**
 * Интерфейс репозитория словаря.
 */
public interface DictionaryRepository {

    /**
     * Возвращает все записи словаря.
     */
    Map<String, String> findAll();

    /**
     * Находит запись по ключу.
     *
     * @param key ключ для поиска.
     * @return значение, соответствующее ключу, или null, если запись не найдена.
     */
    String findByKey(String key);

    /**
     * Сохраняет новую запись или обновляет существующую.
     *
     * @param key   ключ записи.
     * @param value значение записи.
     */
    void save(String key, String value);

    /**
     * Удаляет запись по ключу.
     *
     * @param key ключ для удаления записи.
     */
    void deleteByKey(String key);

    /**
     * Проверяет существование записи с данным ключом.
     *
     * @param key ключ для проверки.
     * @return true, если запись существует, иначе false.
     */
    boolean existsByKey(String key);
}
