package com.maltsev.parser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@ToString
@Document(collection = "requirements")
public class Requirements {

    @Field(name = "name")
    private String name;

    @Field(name = "amount")
    private int amount;

    @Field(name = "date")
    private String date;

    public Requirements(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

}
