package com.maltsev.parser.repository;

import com.maltsev.parser.entity.Vacancy;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface VacancyRepository extends MongoRepository<Vacancy, ObjectId> {

    public List<Vacancy> findVacanciesByDateOrderByAmountDesc(String date);

}
