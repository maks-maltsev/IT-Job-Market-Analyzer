package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Subscriber;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriberRepository extends MongoRepository<Subscriber, ObjectId> {

    public Subscriber findFirstByEmail(String email);

    public boolean existsByEmail(String email);

    public List<Subscriber> findSubscribersByConfirmationIsTrue();
}
