package dev.filinhat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "five_digit_dictionary")
public class FiveDigitDictionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
