package dev.filinhat.repository;

import dev.filinhat.entity.DictionaryEntity;
import dev.filinhat.entity.ValidatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidatorRepository extends JpaRepository<ValidatorEntity, Long> {

    Optional<ValidatorEntity> findByDictionary(DictionaryEntity dictionary);

    void deleteByDictionary(DictionaryEntity dictionary);
}
