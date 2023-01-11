package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Framework;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FrameworkRepository extends MongoRepository<Framework, ObjectId> {
    public List<Framework> findFrameworksByDateOrderByAmountDesc(String date);

}
