package dev.filinhat.controller;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.service.DictionaryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dictionary/five-digit")
public class FiveDigitDictionaryController {

    private final DictionaryService fiveDigitDictionaryService;

    public FiveDigitDictionaryController(@Qualifier("fiveDigitDictionaryDbService") DictionaryService fiveDigitDictionaryService) {
        this.fiveDigitDictionaryService = fiveDigitDictionaryService;
    }

    @GetMapping
    public List<DictionaryDto> readEntries() {
        return fiveDigitDictionaryService.readEntries();
    }

    @GetMapping("/{key}")
    public DictionaryDto getEntry(@PathVariable String key) {
        return fiveDigitDictionaryService.searchEntry(key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@RequestBody DictionaryDto entryDto) {
        fiveDigitDictionaryService.addEntry(entryDto);
    }

    @DeleteMapping("/{key}")
    public void deleteEntry(@PathVariable String key) {
        fiveDigitDictionaryService.deleteEntry(key);
    }
}
