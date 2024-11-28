package dev.filinhat.service;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.dto.ValidatorDto;
import dev.filinhat.entity.DictionaryEntity;
import dev.filinhat.entity.ValidatorEntity;
import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.repository.ValidatorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final DictionaryRepository dictionaryRepository;
    private final ValidatorRepository validatorRepository;

    public AdminService(DictionaryRepository dictionaryRepository, ValidatorRepository validatorRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.validatorRepository = validatorRepository;
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
}
