package dev.filinhat.repository;

import dev.filinhat.entity.FourLetterDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FourLetterDictionaryDbRepository extends JpaRepository<FourLetterDictionary, String> {
}
