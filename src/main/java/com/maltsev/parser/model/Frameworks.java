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
@Document(collection = "frameworks")
public class Frameworks {

    @Field(name = "name")
    String name;

    @Field(name = "amount")
    int amount;

    @Field(name = "date")
    String date;

    public Frameworks(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}


