package com.maltsev.vacancies_parser.repository;

import com.maltsev.vacancies_parser.entity.Subscriber;
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
