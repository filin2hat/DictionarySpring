package dev.filinhat.service;

import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.validator.DictionaryValidator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FileDictionaryService implements DictionaryService {
    private DictionaryValidator validator;
    private DictionaryRepository repository;

    public void setValidator(DictionaryValidator validator) {
        this.validator = validator;
    }

    public void setRepository(DictionaryRepository repository) {
        this.repository = repository;
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
