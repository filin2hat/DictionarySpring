package dev.filinhat.service;

import dev.filinhat.entity.FiveDigitDictionary;
import dev.filinhat.repository.FiveDigitDictionaryDbRepository;
import dev.filinhat.validator.DictionaryValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FiveDigitDictionaryDbService implements DictionaryService {

    private final DictionaryValidator validator;
    private final FiveDigitDictionaryDbRepository repository;

    public FiveDigitDictionaryDbService(
            @Qualifier("fiveDigitValidator") DictionaryValidator validator,
            FiveDigitDictionaryDbRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> readEntries() {
        return repository.findAll()
                .stream()
                .collect(Collectors.toMap(FiveDigitDictionary::getKey, FiveDigitDictionary::getValue));
    }

    @Override
    @Transactional
    public void addEntry(String key, String value) {
        if (!validator.isValidKey(key)) {
            throw new IllegalArgumentException("Key is invalid for four-letter dictionary");
        }
        FiveDigitDictionary entry = new FiveDigitDictionary();
        entry.setKey(key);
        entry.setValue(value);
        repository.save(entry);
    }

    @Override
    @Transactional
    public void deleteEntry(String key) {
        if (!repository.existsById(key)) {
            throw new IllegalArgumentException("Entry with key '" + key + "' does not exist");
        }
        repository.deleteById(key);
    }

    @Override
    @Transactional(readOnly = true)
    public String searchEntry(String key) {
        return repository.findById(key)
                .map(FiveDigitDictionary::getValue)
                .orElse(null);
    }
}
