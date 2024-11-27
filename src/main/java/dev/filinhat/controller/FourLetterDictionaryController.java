package dev.filinhat.controller;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.service.DictionaryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dictionary/four-letter")
public class FourLetterDictionaryController {

    private final DictionaryService fourLetterDictionaryService;

    public FourLetterDictionaryController(@Qualifier("fourLetterDictionaryDbService") DictionaryService fourLetterDictionaryService) {
        this.fourLetterDictionaryService = fourLetterDictionaryService;
    }

    @GetMapping
    public List<DictionaryDto> readEntries() {
        return fourLetterDictionaryService.readEntries();
    }

    @GetMapping("/{key}")
    public DictionaryDto getEntry(@PathVariable String key) {
        return fourLetterDictionaryService.searchEntry(key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@RequestBody DictionaryDto entryDto) {
        fourLetterDictionaryService.addEntry(entryDto);
    }

    @DeleteMapping("/{key}")
    public void deleteEntry(@PathVariable String key) {
        fourLetterDictionaryService.deleteEntry(key);
    }
}
