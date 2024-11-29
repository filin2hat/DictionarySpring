package dev.filinhat.service;

import dev.filinhat.dto.EntryDto;
import dev.filinhat.entity.Entry;
import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.repository.EntryRepository;
import dev.filinhat.repository.ValidatorRepository;
import dev.filinhat.validation.KeyValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService{

    private final DictionaryRepository dictionaryRepository;
    private final ValidatorRepository validatorRepository;
    private final EntryRepository entryRepository;
    private final KeyValidator keyValidator; // Новый компонент

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository,
                                 ValidatorRepository validatorRepository,
                                 EntryRepository entryRepository,
                                 KeyValidator keyValidator) {
        this.dictionaryRepository = dictionaryRepository;
        this.validatorRepository = validatorRepository;
        this.entryRepository = entryRepository;
        this.keyValidator = keyValidator;
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

        keyValidator.validate(dictionaryCode, dto, validator);

        var entry = new Entry();
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

    @Override
    @Transactional
    public void updateEntry(String dictionaryCode, String key, EntryDto dto) {
        var dictionary = dictionaryRepository.findByCode(dictionaryCode)
                .orElseThrow(() -> new IllegalArgumentException("Dictionary not found"));
        var validator = validatorRepository.findByDictionary(dictionary)
                .orElseThrow(() -> new IllegalStateException("Validator not configured"));

        keyValidator.validate(dictionaryCode, dto, validator);

        var entry = entryRepository.findByDictionaryAndKey(dictionary, key)
                .orElseThrow(() -> new IllegalArgumentException("Entry not found"));
        entry.setValue(dto.value());
        entryRepository.save(entry);
    }
}
