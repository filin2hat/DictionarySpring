package dev.filinhat.controller;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.dto.ValidatorDto;
import dev.filinhat.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/admin/dictionaries")
public class DictionaryAdminController {

    private final AdminService dictionaryService;

    public DictionaryAdminController(AdminService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/all")
    public List<String> getAllDictionaries() {
        return dictionaryService.getDictionariesDescription();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDictionary(@RequestBody DictionaryDto dictionaryDto) {
        dictionaryService.createDictionary(dictionaryDto);
    }

    @DeleteMapping("/{dictionaryCode}")
    public void deleteDictionary(@PathVariable String dictionaryCode) {
        dictionaryService.deleteDictionary(dictionaryCode);
    }

    @PostMapping("/{dictionaryCode}/validator")
    @ResponseStatus(HttpStatus.CREATED)
    public void setValidationRule(@PathVariable String dictionaryCode, @RequestBody ValidatorDto validatorDto) {
        dictionaryService.setValidationRule(dictionaryCode, validatorDto);
    }
}
