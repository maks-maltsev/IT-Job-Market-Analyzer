package com.maltsev.parser.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Getter
@Setter
@ToString
@Document(collection = "requirements")
public class Requirement {

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
