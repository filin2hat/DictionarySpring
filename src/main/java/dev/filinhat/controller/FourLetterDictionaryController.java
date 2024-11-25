package dev.filinhat.controller;

import dev.filinhat.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            return fourLetterDictionaryService.searchEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@RequestParam String key, @RequestParam String value) {
        try {
            fourLetterDictionaryService.addEntry(key, value);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{key}")
    public void deleteEntry(@PathVariable String key) {
        try {
            fourLetterDictionaryService.deleteEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
