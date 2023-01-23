package com.maltsev.parser.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@ToString
@Getter
@Setter
@Document(collection = "vacancies")
public class Vacancy {

    @MongoId
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "name")
    private String name;

    @Field(name = "amount")
    private int amount;

    @Field(name = "date")
    private String date;

    public Vacancy(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}
