package dev.filinhat.controller;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.dto.ValidatorDto;
import dev.filinhat.service.AdministrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/admin/dictionaries")
@AllArgsConstructor
public class AdministrationController {

    private final AdministrationService service;

    @GetMapping
    public List<String> getAllDictionaries() {
        return service.getDictionariesDescription();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDictionary(@RequestBody DictionaryDto dictionaryDto) {
        service.createDictionary(dictionaryDto);
    }

    @PutMapping("/{dictionaryCode}")
    public void updateDictionary(
            @PathVariable String dictionaryCode,
            @RequestBody DictionaryDto updatedDictionaryDto) {
        service.updateDictionary(dictionaryCode, updatedDictionaryDto);
    }

    @DeleteMapping("/{dictionaryCode}")
    public void deleteDictionary(@PathVariable String dictionaryCode) {
        service.deleteDictionary(dictionaryCode);
    }

    @PostMapping("/{dictionaryCode}/validator")
    @ResponseStatus(HttpStatus.CREATED)
    public void setValidationRule(@PathVariable String dictionaryCode, @RequestBody ValidatorDto validatorDto) {
        service.setValidationRule(dictionaryCode, validatorDto);
    }
}
