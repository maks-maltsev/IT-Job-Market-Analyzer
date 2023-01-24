package com.maltsev.vacancies_parser.repository;

import com.maltsev.vacancies_parser.entity.Vacancy;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends MongoRepository<Vacancy, ObjectId> {

    List<Vacancy> findVacanciesByDateOrderByAmountDesc(String date);

}
