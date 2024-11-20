package dev.filinhat.controller;

import dev.filinhat.service.DictionaryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("api/v1/dictionary")
public class DictionaryController {

    private final @Qualifier("fourLetterDictionaryDbService") DictionaryService fourLetterDictionaryService;
    private final @Qualifier("fiveDigitDictionaryDbService") DictionaryService fiveDigitDictionaryService;

    public DictionaryController(
            @Qualifier("fourLetterDictionaryDbService") DictionaryService fourLetterDictionaryService,
            @Qualifier("fiveDigitDictionaryDbService") DictionaryService fiveDigitDictionaryService) {
        this.fourLetterDictionaryService = fourLetterDictionaryService;
        this.fiveDigitDictionaryService = fiveDigitDictionaryService;
    }

    @GetMapping("/four-letter")
    public Map<String, String> fourLetterDictionary() {
        return fourLetterDictionaryService.readEntries();
    }

    @GetMapping("/five-digit")
    public Map<String, String> fiveDigitDictionary() {
        return fiveDigitDictionaryService.readEntries();
    }

    @GetMapping("/four-letter/{key}")
    public String getEntryByFourLetterKey(@PathVariable String key) {
        try {
            return fourLetterDictionaryService.searchEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/five-digit/{key}")
    public String getEntryByFiveDigitKey(@PathVariable String key) {
        try {
            return fiveDigitDictionaryService.searchEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/four-letter")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntryToFourLetterDictionary(@RequestParam String key, @RequestParam String value) {
        try {
            fourLetterDictionaryService.addEntry(key, value);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/five-digit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntryToFiveDigitDictionary(@RequestParam String key, @RequestParam String value) {
        try {
            fiveDigitDictionaryService.addEntry(key, value);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/four-letter/{key}")
    public void deleteEntryFromFourLetterDictionary(@PathVariable String key) {
        try {
            fourLetterDictionaryService.deleteEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/five-digit/{key}")
    public void deleteEntryFromFiveDigitDictionary(@PathVariable String key) {
        try {
            fiveDigitDictionaryService.deleteEntry(key);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
