package dev.filinhat.repository;

import dev.filinhat.entity.Dictionary;
import dev.filinhat.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByDictionary(Dictionary dictionary);

    Optional<Entry> findByDictionaryAndKey(Dictionary dictionary, String key);

    void deleteByDictionaryAndKey(Dictionary dictionary, String key);

    boolean existsByDictionaryAndKey(Dictionary dictionary, String key);

    void deleteByDictionary(Dictionary dictionary);
}
