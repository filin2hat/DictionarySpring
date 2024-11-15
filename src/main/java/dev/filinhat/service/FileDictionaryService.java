package dev.filinhat.service;

import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.repository.MapRepository;
import dev.filinhat.validator.DictionaryValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileDictionaryService implements DictionaryService {
    private final DictionaryValidator validator;
    private final DictionaryRepository repository;

    public FileDictionaryService(DictionaryValidator validator, Path filePath) {
        this.validator = validator;

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("\nНе удалось создать файл: " + filePath);
        }

        this.repository = new MapRepository(filePath);
    }

    @Override
    public Map<String, String> readEntries() {
        return repository.findAll();
    }

    @Override
    public void addEntry(String key, String value) {
        if (validator.isValidKey(key)) {
            repository.save(key, value);
            System.out.println("\nЗапись добавлена.");
        } else {
            throw new IllegalArgumentException("\n!!!Недопустимый формат ключа!!!");
        }
    }

    @Override
    public void deleteEntry(String key) {
        if (repository.existsByKey(key)) {
            repository.deleteByKey(key);
            System.out.println("\nЗапись удалена.");
        } else {
            System.out.println("\nЗапись с таким ключом не найдена.");
        }
    }

    @Override
    public String searchEntry(String key) {
        if (repository.existsByKey(key)) {
            return repository.findByKey(key);
        } else {
            System.out.println("\nЗапись с таким ключом не найдена.");
            return null;
        }
    }
}
