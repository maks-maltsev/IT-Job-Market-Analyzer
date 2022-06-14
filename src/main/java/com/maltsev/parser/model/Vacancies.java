package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@ToString
@Getter
@Setter
@Document(collection = "vacancies")
public class Vacancies {

    @Field(name = "name")
    private String name;

    @Field(name = "amount")
    private int amount;

    @Field(name = "date")
    private String date;

    public Vacancies(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}
