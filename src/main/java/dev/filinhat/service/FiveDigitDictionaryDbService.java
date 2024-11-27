package dev.filinhat.service;

import dev.filinhat.dto.DictionaryDto;
import dev.filinhat.entity.FiveDigitDictionaryEntity;
import dev.filinhat.repository.FiveDigitDictionaryDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiveDigitDictionaryDbService implements DictionaryService {
    private final FiveDigitDictionaryDbRepository repository;

    public FiveDigitDictionaryDbService(FiveDigitDictionaryDbRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DictionaryDto> readEntries() {
        return repository.findAll()
                .stream()
                .map(entry -> new DictionaryDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addEntry(DictionaryDto dto) {
        var entry = new FiveDigitDictionaryEntity();
        entry.setKey(dto.key());
        entry.setValue(dto.value());
        repository.save(entry);
    }

    @Override
    @Transactional
    public void deleteEntry(String key) {
        if (!repository.existsByKey(key)) {
            throw new IllegalArgumentException("Entry with key '" + key + "' does not exist");
        }
        repository.deleteByKey(key);
    }

    @Override
    @Transactional(readOnly = true)
    public DictionaryDto searchEntry(String key) {
        return repository.findByKey(key)
                .map(entry -> new DictionaryDto(entry.getKey(), entry.getValue()))
                .orElse(null);
    }
}
