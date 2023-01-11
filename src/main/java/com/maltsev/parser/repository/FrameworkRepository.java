package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Framework;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FrameworkRepository extends MongoRepository<Framework, ObjectId> {
    List<Framework> findFrameworksByDateOrderByAmountDesc(String date);
////Select stats by date
//    @Query(value = "SELECT name FROM framework WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<String> selectFrameworksArrayWhereDateIs(String date);
//
//    @Query(value = "SELECT amount FROM framework WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<Double> selectFrameworksAmountArrayWhereDateIs(String date);

}
