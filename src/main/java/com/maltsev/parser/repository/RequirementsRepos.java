package com.maltsev.parser.repository;

import com.maltsev.parser.model.Requirements;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RequirementsRepos extends CrudRepository<Requirements, Long> {

//Select stats by date
    @Query(value = "SELECT name FROM requirement WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectRequirementsArrayWhereDateIs(String date);

    @Query(value = "SELECT amount FROM requirement WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Double> selectRequirementsAmountArrayWhereDateIs(String date);
}
