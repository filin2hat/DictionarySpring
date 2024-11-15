package dev.filinhat.repository;

import dev.filinhat.util.FileUtils;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MapRepository implements DictionaryRepository {
    private final Path filePath;
    private final Map<String, String> entries;

    public MapRepository(Path filePath) {
        this.filePath = filePath;
        this.entries = FileUtils.readEntriesFromFile(filePath);
    }

    @Override
    public Map<String, String> findAll() {
        return new HashMap<>(entries);
    }

    @Override
    public String findByKey(String key) {
        return entries.get(key);
    }

    @Override
    public void save(String key, String value) {
        entries.put(key, value);
        FileUtils.writeEntriesToFile(filePath, entries);
    }

    @Override
    public void deleteByKey(String key) {
        entries.remove(key);
        FileUtils.writeEntriesToFile(filePath, entries);
    }

    @Override
    public boolean existsByKey(String key) {
        return entries.containsKey(key);
    }
}
