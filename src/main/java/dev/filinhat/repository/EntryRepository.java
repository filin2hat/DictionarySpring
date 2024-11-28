package dev.filinhat.repository;

import dev.filinhat.entity.DictionaryEntity;
import dev.filinhat.entity.EntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<EntryEntity, Long> {
    List<EntryEntity> findByDictionary(DictionaryEntity dictionary);

    Optional<EntryEntity> findByDictionaryAndKey(DictionaryEntity dictionary, String key);

    void deleteByDictionaryAndKey(DictionaryEntity dictionary, String key);

    boolean existsByDictionaryAndKey(DictionaryEntity dictionary, String key);

    void deleteByDictionary(DictionaryEntity dictionary);
}
