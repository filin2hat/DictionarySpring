package dev.filinhat.repository;

import dev.filinhat.entity.Dictionary;
import dev.filinhat.entity.Validator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidatorRepository extends JpaRepository<Validator, Long> {

    Optional<Validator> findByDictionary(Dictionary dictionary);

    void deleteByDictionary(Dictionary dictionary);
}
