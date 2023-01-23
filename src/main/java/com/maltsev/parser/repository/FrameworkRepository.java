package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Framework;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameworkRepository extends MongoRepository<Framework, ObjectId> {
    public List<Framework> findFrameworksByDateOrderByAmountDesc(String date);

}
