package com.maltsev.parser.repository;

import com.maltsev.parser.model.Vacancies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface VacanciesRepos extends JpaRepository<Vacancies, Long> {

//Select stats by date
    @Query(value = "SELECT name FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectLanguagesArrayWhereDateIs(String date);

    @Query(value = "SELECT amount FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Double> selectLanguagesAmountArrayWhereDateIs(String date);
}
