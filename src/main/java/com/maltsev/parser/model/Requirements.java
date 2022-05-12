package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Table(name = "requirement")
@Entity
public class Requirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int amount;
    private String date;

    public Requirements() {}

    public Requirements(Long id, String name, int amount, String date) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public Requirements(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}
