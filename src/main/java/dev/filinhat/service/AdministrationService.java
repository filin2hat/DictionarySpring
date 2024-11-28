package dev.filinhat.service;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.dto.ValidatorDto;
import dev.filinhat.entity.DictionaryEntity;
import dev.filinhat.entity.ValidatorEntity;
import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.repository.EntryRepository;
import dev.filinhat.repository.ValidatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministrationService {

    private final DictionaryRepository dictionaryRepository;
    private final ValidatorRepository validatorRepository;
    private final EntryRepository entryRepository;

    public AdministrationService(
            DictionaryRepository dictionaryRepository,
            ValidatorRepository validatorRepository,
            EntryRepository entryRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.validatorRepository = validatorRepository;
        this.entryRepository = entryRepository;
    }

    @Transactional
    public void createDictionary(DictionaryDto dto) {
        var dictionary = new DictionaryEntity();
        dictionary.setCode(dto.code());
        dictionary.setName(dto.name());
        dictionaryRepository.save(dictionary);
    }

    @Transactional
    public void setValidationRule(String dictionaryCode, ValidatorDto dto) {
        var dictionary = dictionaryRepository.findByCode(dictionaryCode)
                .orElseThrow(() -> new IllegalArgumentException("Dictionary not found"));

        var validator = validatorRepository.findByDictionary(dictionary)
                .orElse(new ValidatorEntity());
        validator.setDictionary(dictionary);
        validator.setKeyLength(dto.keyLength());
        validator.setKeyPattern(dto.keyPattern());
        validatorRepository.save(validator);
    }

    @Transactional
    public void deleteDictionary(String dictionaryName) {
        var dictionary = dictionaryRepository.findByCode(dictionaryName)
                .orElseThrow(() -> new IllegalArgumentException("Dictionary not found"));

        validatorRepository.deleteByDictionary(dictionary);
        entryRepository.deleteByDictionary(dictionary);
        dictionaryRepository.delete(dictionary);
    }

    @Transactional(readOnly = true)
    public List<String> getDictionariesDescription() {
        return dictionaryRepository.findAll().stream()
                .map(dictionary -> {
                    var validator = validatorRepository.findByDictionary(dictionary).orElse(null);
                    String validatorDescription = (validator != null)
                            ? String.format("Key Length: %d, Pattern: %s", validator.getKeyLength(), validator.getKeyPattern())
                            : "No validation rule";
                    return String.format("Dictionary: %s, Validation Rule: [%s]", dictionary.getCode(), validatorDescription);
                })
                .collect(Collectors.toList());
    }
}
