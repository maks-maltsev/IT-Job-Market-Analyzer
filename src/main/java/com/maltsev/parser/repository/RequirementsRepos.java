package com.maltsev.parser.repository;

import com.maltsev.parser.model.Requirements;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RequirementsRepos extends CrudRepository<Requirements, Long> {

//Select all requirements/amount info
    @Query(value = "SELECT name FROM requirement ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectRequirementsArray();
    @Query(value = "SELECT amount FROM requirement ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectRequirementsAmountArray();

//Select stats by date
    @Query(value = "SELECT name FROM requirement WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectRequirementsArrayWhereDateIs(String date);

    @Query(value = "SELECT amount FROM requirement WHERE date = (?1) ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectRequirementsAmountArrayWhereDateIs(String date);
}
