package com.maltsev.parser.repository;

import com.maltsev.parser.model.Frameworks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface FrameworksRepos extends CrudRepository<Frameworks, Long> {

//Add new framework/amount
    @Query(value = "INSERT INTO framework (name, amount, date) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertFrameworkData (String name, int amount, String date);

//Select all frameworks/amount info
    @Query(value = "SELECT name FROM framework ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectFrameworksArray();

    @Query(value = "SELECT amount FROM framework ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectFrameworksAmountArray();
}
