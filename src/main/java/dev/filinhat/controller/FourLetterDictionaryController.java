package dev.filinhat.controller;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/dictionary/four-letter")
public class FourLetterDictionaryController {

    private final DictionaryService fourLetterDictionaryService;

    public FourLetterDictionaryController(DictionaryService fourLetterDictionaryService) {
        this.fourLetterDictionaryService = fourLetterDictionaryService;
    }

    @GetMapping
    public Map<String, String> readEntries() {
        return fourLetterDictionaryService.readEntries();
    }

    @GetMapping("/{key}")
    public String getEntry(@PathVariable String key) {
        return fourLetterDictionaryService.searchEntry(key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@RequestBody DictionaryDto entryDto) {
        fourLetterDictionaryService.addEntry(entryDto.key(), entryDto.value());
    }

    @DeleteMapping("/{key}")
    public void deleteEntry(@PathVariable String key) {
        fourLetterDictionaryService.deleteEntry(key);
    }
}
