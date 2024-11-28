package dev.filinhat.controller;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.dto.ValidatorDto;
import dev.filinhat.service.AdministrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/admin/dictionaries")
public class AdministrationController {

    private final AdministrationService service;

    public AdministrationController(AdministrationService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<String> getAllDictionaries() {
        return service.getDictionariesDescription();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDictionary(@RequestBody DictionaryDto dictionaryDto) {
        service.createDictionary(dictionaryDto);
    }

    @DeleteMapping("/{dictionaryName}")
    public void deleteDictionary(@PathVariable String dictionaryName) {
        service.deleteDictionary(dictionaryName);
    }

    @PostMapping("/{dictionaryName}/validator")
    @ResponseStatus(HttpStatus.CREATED)
    public void setValidationRule(@PathVariable String dictionaryName, @RequestBody ValidatorDto validatorDto) {
        service.setValidationRule(dictionaryName, validatorDto);
    }
}
