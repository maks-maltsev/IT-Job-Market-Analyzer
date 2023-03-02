package com.maltsev.vacanciesparser.repository;

import com.maltsev.vacanciesparser.entity.Framework;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameworkRepository extends MongoRepository<Framework, ObjectId> {
    List<Framework> findFrameworksByDateOrderByAmountDesc(String date);
}
