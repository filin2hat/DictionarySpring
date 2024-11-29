package dev.filinhat.controller;

import dev.filinhat.dto.EntryDto;
import dev.filinhat.service.DictionaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("api/v2/dictionary")
@AllArgsConstructor
public class DictionaryController {

    private final DictionaryService service;

    @GetMapping("/{dictionaryCode}")
    public Map<String, String> readEntries(@PathVariable String dictionaryCode) {
        return service.getEntries(dictionaryCode)
                .stream()
                .collect(toMap(EntryDto::key, EntryDto::value));
    }

    @GetMapping("/{dictionaryCode}/entry/{key}")
    public EntryDto getEntry(@PathVariable String dictionaryCode, @PathVariable String key) {
        return service.searchEntry(dictionaryCode, key);
    }

    @PostMapping("/{dictionaryCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@PathVariable String dictionaryCode, @RequestBody EntryDto entryDto) {
        service.addEntry(dictionaryCode, entryDto);
    }

    @PutMapping("/{dictionaryCode}/entry/{key}")
    public void updateEntry(
            @PathVariable String dictionaryCode,
            @PathVariable String key,
            @RequestBody EntryDto updatedEntryDto) {
        service.updateEntry(dictionaryCode, key, updatedEntryDto);
    }

    @DeleteMapping("/{dictionaryCode}/{key}")
    public void deleteEntry(@PathVariable String dictionaryCode, @PathVariable String key) {
        service.deleteEntry(dictionaryCode, key);
    }
}
