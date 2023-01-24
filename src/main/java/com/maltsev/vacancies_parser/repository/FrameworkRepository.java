package com.maltsev.vacancies_parser.repository;

import com.maltsev.vacancies_parser.entity.Framework;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameworkRepository extends MongoRepository<Framework, ObjectId> {
    List<Framework> findFrameworksByDateOrderByAmountDesc(String date);

}
