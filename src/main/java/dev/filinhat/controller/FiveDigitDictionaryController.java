package dev.filinhat.controller;

import dev.filinhat.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/dictionary/five-digit")
public class FiveDigitDictionaryController {

    private final DictionaryService fiveDigitDictionaryService;

    public FiveDigitDictionaryController(DictionaryService fiveDigitDictionaryService) {
        this.fiveDigitDictionaryService = fiveDigitDictionaryService;
    }

    @GetMapping
    public Map<String, String> readEntries() {
        return fiveDigitDictionaryService.readEntries();
    }

    @GetMapping("/{key}")
    public String getEntry(@PathVariable String key) {
        return fiveDigitDictionaryService.searchEntry(key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@RequestParam String key, @RequestParam String value) {
        fiveDigitDictionaryService.addEntry(key, value);
    }

    @DeleteMapping("/{key}")
    public void deleteEntry(@PathVariable String key) {
        fiveDigitDictionaryService.deleteEntry(key);
    }
}
