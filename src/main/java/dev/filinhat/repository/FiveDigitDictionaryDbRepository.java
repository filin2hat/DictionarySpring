package dev.filinhat.repository;

import dev.filinhat.entity.FiveDigitDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiveDigitDictionaryDbRepository extends JpaRepository<FiveDigitDictionary, String> {
}
