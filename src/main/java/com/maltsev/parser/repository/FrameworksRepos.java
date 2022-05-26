package com.maltsev.parser.repository;

import com.maltsev.parser.model.Frameworks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface FrameworksRepos extends CrudRepository<Frameworks, Long> {

//Select stats by date
    @Query(value = "SELECT name FROM framework WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectFrameworksArrayWhereDateIs(String date);

    @Query(value = "SELECT amount FROM framework WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Double> selectFrameworksAmountArrayWhereDateIs(String date);

}
