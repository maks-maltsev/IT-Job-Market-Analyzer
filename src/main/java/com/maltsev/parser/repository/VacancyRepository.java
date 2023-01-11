package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Vacancy;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VacancyRepository extends MongoRepository<Vacancy, ObjectId> {
    List<Vacancy> findVacanciesByDateOrderByAmountDesc(String date);
////Select stats by date
//    @Query(value = "SELECT name FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<String> selectLanguagesArrayWhereDateIs(String date);
//
//    @Query(value = "SELECT amount FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<Double> selectLanguagesAmountArrayWhereDateIs(String date);
}
