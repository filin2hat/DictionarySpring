package dev.filinhat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "entry")
@Getter
@Setter
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dictionary_id", nullable = false)
    private Dictionary dictionary;

    private String key;

    private String value;
}
