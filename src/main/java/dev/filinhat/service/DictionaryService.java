package dev.filinhat.service;

import dev.filinhat.dto.DictionaryDto;

import java.util.List;

/**
 * Интерфейс сервиса словаря.
 */
public interface DictionaryService {

    /**
     * Возвращает все записи из словаря.
     */
    List<DictionaryDto> readEntries();

    /**
     * Добавляет новую запись в словарь.
     *
     * @param dto запись словаря.
     * @throws IllegalArgumentException если ключ не соответствует требованиям валидатора.
     */
    void addEntry(DictionaryDto dto);

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
     * @return DTO записи или null, если запись не найдена.
     */
    DictionaryDto searchEntry(String key);
}
