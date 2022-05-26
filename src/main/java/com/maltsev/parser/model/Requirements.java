package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "requirement")
public class Requirements {

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

    public Requirements() {}

    public Requirements(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

}
