package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "framework")
public class Frameworks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "amount")
    int amount;

    @Column(name = "date")
    String date;

    public Frameworks() {
    }

    public Frameworks(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}


