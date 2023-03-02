package com.maltsev.vacanciesparser.repository;

import com.maltsev.vacanciesparser.entity.Vacancy;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends MongoRepository<Vacancy, ObjectId> {
    List<Vacancy> findVacanciesByDateOrderByAmountDesc(String date);
}
