package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Requirement;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RequirementRepository extends MongoRepository<Requirement, ObjectId> {
    public List<Requirement> findRequirementsByDateOrderByAmountDesc(String date);
}
