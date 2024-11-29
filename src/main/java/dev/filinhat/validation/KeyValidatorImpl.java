package dev.filinhat.validation;

import dev.filinhat.dto.EntryDto;
import dev.filinhat.entity.Validator;
import org.springframework.stereotype.Component;

@Component
public class KeyValidatorImpl implements KeyValidator {

    @Override
    public void validate(String dictionaryCode, EntryDto entryDto, Validator validator) {
        if (entryDto.key().length() != validator.getKeyLength()) {
            throw new IllegalArgumentException(
                    String.format("Invalid key length for dictionary '%s'. Expected: %d, Got: %d",
                            dictionaryCode, validator.getKeyLength(), entryDto.key().length()));
        }

        if (!entryDto.key().matches(validator.getKeyPattern())) {
            throw new IllegalArgumentException(
                    String.format("Invalid key pattern for dictionary '%s'. Expected pattern: %s",
                            dictionaryCode, validator.getKeyPattern()));
        }
    }
}
