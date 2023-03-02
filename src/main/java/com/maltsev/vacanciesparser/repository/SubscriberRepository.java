package com.maltsev.vacanciesparser.repository;

import com.maltsev.vacanciesparser.entity.Subscriber;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber, ObjectId> {

    Subscriber findFirstByEmail(String email);
    boolean existsByEmail(String email);
    List<Subscriber> findSubscribersByConfirmationIsTrue();

}
