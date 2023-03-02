package com.maltsev.vacanciesparser.entity;

import lombok.*;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subscribers")
public class Subscriber {

    @MongoId
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "userEmail")
    private String email;

    @Field(name = "confirmEmail")
    private boolean confirmation;

    @Field(name = "code")
    private int code;

    public Subscriber(String email, boolean confirmation, int code) {
        this.email = email;
        this.confirmation = confirmation;
        this.code = code;
    }

}