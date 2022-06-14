package com.maltsev.parser.repository;

import com.maltsev.parser.model.Vacancies;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface VacanciesRepos extends MongoRepository<Vacancies, ObjectId> {
    List<Vacancies> findVacanciesByDateOrderByAmountDesc(String date);
////Select stats by date
//    @Query(value = "SELECT name FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<String> selectLanguagesArrayWhereDateIs(String date);
//
//    @Query(value = "SELECT amount FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
//    ArrayList<Double> selectLanguagesAmountArrayWhereDateIs(String date);
}
