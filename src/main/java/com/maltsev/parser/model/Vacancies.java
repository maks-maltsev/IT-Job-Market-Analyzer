package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "language")
public class Vacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date")
    private String date;

    public Vacancies(String name, int amount, String date) {

        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public Vacancies() {

    }
}
