package dev.filinhat.service;

import dev.filinhat.dto.EntryDto;
import dev.filinhat.entity.EntryEntity;
import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.repository.EntryRepository;
import dev.filinhat.repository.ValidatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;
    private final ValidatorRepository validatorRepository;
    private final EntryRepository entryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository,
                             ValidatorRepository validatorRepository,
                             EntryRepository entryRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.validatorRepository = validatorRepository;
        this.entryRepository = entryRepository;
    }

    @Transactional(readOnly = true)
    public List<EntryDto> getEntries(String dictionaryCode) {
        var dictionary = dictionaryRepository.findByCode(dictionaryCode)
                .orElseThrow(() -> new IllegalArgumentException("Dictionary not found"));
        return entryRepository.findByDictionary(dictionary).stream()
                .map(entry -> new EntryDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addEntry(String dictionaryCode, EntryDto dto) {
        var dictionary = dictionaryRepository.findByCode(dictionaryCode)
                .orElseThrow(() -> new IllegalArgumentException("Dictionary not found"));
        var validator = validatorRepository.findByDictionary(dictionary)
                .orElseThrow(() -> new IllegalStateException("Validator not configured"));

        if (dto.key().length() != validator.getKeyLength() || !dto.key().matches(validator.getKeyPattern())) {
            throw new IllegalArgumentException("Key is invalid for the dictionary");
        }

        var entry = new EntryEntity();
        entry.setDictionary(dictionary);
        entry.setKey(dto.key());
        entry.setValue(dto.value());
        entryRepository.save(entry);
    }

    @Transactional
    public void deleteEntry(String dictionaryCode, String key) {
        var dictionary = dictionaryRepository.findByCode(dictionaryCode)
                .orElseThrow(() -> new IllegalArgumentException("Dictionary not found"));
        entryRepository.deleteByDictionaryAndKey(dictionary, key);
    }

    @Transactional
    public EntryDto searchEntry(String dictionaryCode, String key) {
        var dictionary = dictionaryRepository.findByCode(dictionaryCode)
                .orElseThrow(() -> new IllegalArgumentException("Dictionary not found"));
        return entryRepository.findByDictionaryAndKey(dictionary, key)
                .map(entry -> new EntryDto(entry.getKey(), entry.getValue()))
                .orElse(null);
    }
}
