package dev.filinhat.controller;

import dev.filinhat.dto.EntryDto;
import dev.filinhat.service.DictionaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("api/v2/dictionary")
public class DictionaryController {

    private final DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }

    @GetMapping("/{dictionaryName}")
    public Map<String, String> readEntries(@PathVariable String dictionaryName) {
        return service.getEntries(dictionaryName)
                .stream()
                .collect(toMap(EntryDto::key, EntryDto::value));
    }

    @GetMapping("/{dictionaryName}/{key}")
    public EntryDto getEntry(@PathVariable String dictionaryName, @PathVariable String key) {
        return service.searchEntry(dictionaryName, key);
    }

    @PostMapping("/{dictionaryName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEntry(@PathVariable String dictionaryName, @RequestBody EntryDto entryDto) {
        service.addEntry(dictionaryName, entryDto);
    }

    @DeleteMapping("/{dictionaryName}/{key}")
    public void deleteEntry(@PathVariable String dictionaryName, @PathVariable String key) {
        service.deleteEntry(dictionaryName, key);
    }
}
