package dev.filinhat.repository;

import dev.filinhat.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    Optional<Dictionary> findByCode(String code);
}
