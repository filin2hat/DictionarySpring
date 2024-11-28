package dev.filinhat.controller;

import dev.filinhat.dto.EntryDto;
import dev.filinhat.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/{dictionaryCode}")
    public List<EntryDto> readEntries(@PathVariable String dictionaryCode) {
        return dictionaryService.getEntries(dictionaryCode);
    }

    @GetMapping("/{dictionaryCode}/{key}")
    public EntryDto getEntry(@PathVariable String dictionaryCode, @PathVariable String key) {
        return dictionaryService.searchEntry(dictionaryCode, key);
    }

    @PostMapping("/{dictionaryCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@PathVariable String dictionaryCode, @RequestBody EntryDto entryDto) {
        dictionaryService.addEntry(dictionaryCode, entryDto);
    }

    @DeleteMapping("/{dictionaryCode}/{key}")
    public void deleteEntry(@PathVariable String dictionaryCode, @PathVariable String key) {
        dictionaryService.deleteEntry(dictionaryCode, key);
    }
}
