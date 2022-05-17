package com.maltsev.parser.repository;

import com.maltsev.parser.model.Frameworks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface FrameworksRepos extends CrudRepository<Frameworks, Long> {

//Select all frameworks/amount info
    @Query(value = "SELECT name FROM framework ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectFrameworksArray();

    @Query(value = "SELECT amount FROM framework ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectFrameworksAmountArray();

//Select stats by date
    @Query(value = "SELECT name FROM framework WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectFrameworksArrayWhereDateIs(String date);

    @Query(value = "SELECT amount FROM framework WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectFrameworksAmountArrayWhereDateIs(String date);
}
