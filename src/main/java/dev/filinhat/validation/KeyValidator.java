package dev.filinhat.validation;

import dev.filinhat.dto.EntryDto;
import dev.filinhat.entity.Validator;

public interface KeyValidator {

    void validate(String dictionaryCode, EntryDto entryDto, Validator validator);
}
