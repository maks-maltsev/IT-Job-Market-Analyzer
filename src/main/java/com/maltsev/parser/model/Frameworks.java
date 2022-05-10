package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity(name = "framework")
public class Frameworks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    int amount;
    String date;

    public Frameworks() {
    }

    public Frameworks(Long id, String name, int amount, String date) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public Frameworks(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}
