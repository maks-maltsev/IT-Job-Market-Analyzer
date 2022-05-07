package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "language")
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int amount;


    private String date;

    public Languages(String name, int amount, String date) {

        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public Languages(Long id, String name, int amount, String date) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public Languages() {

    }
}
