package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Subscriber;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber, ObjectId> {

    public Subscriber findFirstByEmail(String email);

    public boolean existsByEmail(String email);

    public List<Subscriber> findSubscribersByConfirmationIsTrue();
}
