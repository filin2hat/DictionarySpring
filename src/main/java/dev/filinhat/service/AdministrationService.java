package dev.filinhat.service;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.dto.ValidatorDto;

import java.util.List;

public interface AdministrationService {

    void createDictionary(DictionaryDto dto);

    void setValidationRule(String dictionaryCode, ValidatorDto dto);

    void deleteDictionary(String dictionaryCode);

    List<String> getDictionariesDescription();

    void updateDictionary(String dictionaryCode, DictionaryDto dto);
}
