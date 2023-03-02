package com.maltsev.vacanciesparser.repository;

import com.maltsev.vacanciesparser.entity.Requirement;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends MongoRepository<Requirement, ObjectId> {
    List<Requirement> findRequirementsByDateOrderByAmountDesc(String date);
}
