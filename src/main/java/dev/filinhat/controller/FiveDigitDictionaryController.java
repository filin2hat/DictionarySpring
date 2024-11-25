package dev.filinhat.controller;

import dev.filinhat.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            return fiveDigitDictionaryService.searchEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@RequestParam String key, @RequestParam String value) {
        try {
            fiveDigitDictionaryService.addEntry(key, value);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{key}")
    public void deleteEntry(@PathVariable String key) {
        try {
            fiveDigitDictionaryService.deleteEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
