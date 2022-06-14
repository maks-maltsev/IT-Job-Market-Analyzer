package com.maltsev.parser.repository;

import com.maltsev.parser.model.Requirements;
import com.maltsev.parser.model.Vacancies;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface RequirementsRepos extends MongoRepository<Requirements, ObjectId> {
    List<Requirements> findRequirementsByDateOrderByAmountDesc(String date);
////Select stats by date
//    @Query(value = "SELECT name FROM requirement WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<String> selectRequirementsArrayWhereDateIs(String date);
//
//    @Query(value = "SELECT amount FROM requirement WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<Double> selectRequirementsAmountArrayWhereDateIs(String date);
}
