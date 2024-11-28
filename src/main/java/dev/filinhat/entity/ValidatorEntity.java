package dev.filinhat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "validator")
public class ValidatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dictionary_id", nullable = false)
    private DictionaryEntity dictionary;

    private int keyLength;

    private String keyPattern;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DictionaryEntity getDictionary() {
        return dictionary;
    }

    public void setDictionary(DictionaryEntity dictionary) {
        this.dictionary = dictionary;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public String getKeyPattern() {
        return keyPattern;
    }

    public void setKeyPattern(String keyPattern) {
        this.keyPattern = keyPattern;
    }
}
