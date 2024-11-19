package dev.filinhat.service;

import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.validator.DictionaryValidator;

import java.util.Map;

/**
 * Сервис для работы с файловым словарем
 */
public class FileDictionaryServiceImpl implements DictionaryService {
    private final DictionaryValidator validator;
    private final DictionaryRepository repository;

    public FileDictionaryServiceImpl(DictionaryValidator validator, DictionaryRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    public Map<String, String> readEntries() {
        return repository.findAll();
    }

    @Override
    public void addEntry(String key, String value) {
        if (!validator.isValidKey(key)) {
            throw new IllegalArgumentException("Недопустимый формат ключа: " + key);
        }
        repository.save(key, value);
    }

    @Override
    public void deleteEntry(String key) {
        if (!repository.existsByKey(key)) {
            throw new IllegalArgumentException("Запись с ключом '" + key + "' не найдена.");
        }
        repository.deleteByKey(key);
    }

    @Override
    public String searchEntry(String key) {
        if (!repository.existsByKey(key)) {
            throw new IllegalArgumentException("Запись с ключом '" + key + "' не найдена.");
        }
        return repository.findByKey(key);
    }
}
