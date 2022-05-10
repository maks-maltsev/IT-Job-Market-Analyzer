package com.maltsev.parser.repository;

import com.maltsev.parser.model.Languages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LanguageRepos extends CrudRepository<Languages, Long> {

//Add new language/amount
    @Query(value = "INSERT INTO language (name, amount) VALUES (?1, ?2)", nativeQuery = true)
    void insertLanguageData (String name, int amount);

//Select all languages/amount info
    @Query(value = "SELECT name FROM language ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectLanguagesArray();

    @Query(value = "SELECT amount FROM language ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectLanguagesAmountArray();


}
