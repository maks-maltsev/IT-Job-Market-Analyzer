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
@Document(collection = "frameworks")
public class Framework {
    @MongoId
    @Field(name = "_id")
    private ObjectId id;
    @Field(name = "name")
    String name;
    @Field(name = "amount")
    int amount;
    @Field(name = "date")
    String date;

    public Framework(String name, int amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}


