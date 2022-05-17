package com.maltsev.parser.repository;

import com.maltsev.parser.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LanguageRepos extends JpaRepository<Languages, Long> {

//Select all languages/amount info
    @Query(value = "SELECT name FROM language ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectLanguagesArray(String date);

    @Query(value = "SELECT amount FROM language ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectLanguagesAmountArray();

//Select stats by date
    @Query(value = "SELECT name FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectLanguagesArrayWhereDateIs(String date);

    @Query(value = "SELECT amount FROM language WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectLanguagesAmountArrayWhereDateIs(String date);
}
