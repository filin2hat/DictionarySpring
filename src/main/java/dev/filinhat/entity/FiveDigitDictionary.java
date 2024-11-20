package dev.filinhat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "five_digit_dictionary")
public class FiveDigitDictionary {

    @Id
    @Column(length = 5, nullable = false)
    private String key;

    @Column(nullable = false)
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
