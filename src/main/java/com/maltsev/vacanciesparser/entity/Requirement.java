package com.maltsev.vacanciesparser.entity;

import lombok.*;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * This class represents a requirement entity with its name, amount, and date of creation.
 * It is a POJO that is mapped to a MongoDB document using Spring Data MongoDB.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "requirements")
public class Requirement implements VacancyData {

    @MongoId
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "name")
    private String name;

    @Field(name = "amount")
    private int amount;

    @Field(name = "date")
    private String date;

    public Requirement(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

}
