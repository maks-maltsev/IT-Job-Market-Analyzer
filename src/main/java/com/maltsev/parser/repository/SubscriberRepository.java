package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Subscriber;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriberRepository extends MongoRepository<Subscriber, ObjectId> {

    Subscriber findFirstByEmail(String email);

    boolean existsByEmail(String email);

    List<Subscriber> findSubscribersByConfirmationIsTrue();
}
