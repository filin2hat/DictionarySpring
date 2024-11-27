package dev.filinhat.service;

import dev.filinhat.entity.FourLetterDictionary;
import dev.filinhat.repository.FourLetterDictionaryDbRepository;
import dev.filinhat.validator.DictionaryValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FourLetterDictionaryDbService implements DictionaryService {

    private final DictionaryValidator validator;
    private final FourLetterDictionaryDbRepository repository;

    public FourLetterDictionaryDbService(
            @Qualifier("fourLetterValidator") DictionaryValidator validator,
            FourLetterDictionaryDbRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> readEntries() {
        return repository.findAll()
                .stream()
                .collect(Collectors.toMap(FourLetterDictionary::getKey, FourLetterDictionary::getValue));
    }

    @Override
    @Transactional
    public void addEntry(String key, String value) {
        if (!validator.isValidKey(key)) {
            throw new IllegalArgumentException("Key is invalid for four-letter dictionary");
        }
        FourLetterDictionary entry = new FourLetterDictionary();
        entry.setKey(key);
        entry.setValue(value);
        repository.save(entry);
    }

    @Override
    @Transactional
    public void deleteEntry(String key) {
        if (!repository.existsByKey(key)) {
            throw new IllegalArgumentException("Entry with key '" + key + "' does not exist");
        }
        repository.deleteByKey(key);
    }

    @Override
    @Transactional(readOnly = true)
    public String searchEntry(String key) {
        return repository.findByKey(key)
                .map(FourLetterDictionary::getValue)
                .orElse(null);
    }
}
