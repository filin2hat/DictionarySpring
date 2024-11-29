package dev.filinhat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "validator")
@Getter
@Setter
public class Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dictionary_id", nullable = false)
    private Dictionary dictionary;

    private int keyLength;

    private String keyPattern;
}
