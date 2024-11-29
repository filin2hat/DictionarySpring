package dev.filinhat.service;

import dev.filinhat.dto.EntryDto;

import java.util.List;

public interface DictionaryService {

    List<EntryDto> getEntries(String dictionaryCode);

    void addEntry(String dictionaryCode, EntryDto dto);

    void deleteEntry(String dictionaryCode, String key);

    EntryDto searchEntry(String dictionaryCode, String key);

    void updateEntry(String dictionaryCode, String key, EntryDto dto);
}
