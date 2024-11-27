package dev.filinhat.repository;

import dev.filinhat.entity.FiveDigitDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiveDigitDictionaryDbRepository extends JpaRepository<FiveDigitDictionary, String> {

    Optional<FiveDigitDictionary> findByKey(String key);

    void deleteByKey(String key);

    boolean existsByKey(String key);
}
