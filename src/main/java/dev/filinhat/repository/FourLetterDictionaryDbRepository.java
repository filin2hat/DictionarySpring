package dev.filinhat.repository;

import dev.filinhat.entity.FourLetterDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FourLetterDictionaryDbRepository extends JpaRepository<FourLetterDictionary, String> {

    Optional<FourLetterDictionary> findByKey(String key);

    void deleteByKey(String key);

    boolean existsByKey(String key);
}
