package com.maltsev.parser.repository;

import com.maltsev.parser.model.Requirements;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RequirementsRepos extends CrudRepository<Requirements, Long> {

//Add new requirement/amount
    @Query(value = "INSERT INTO requirement (name, amount) VALUES (?1, ?2)", nativeQuery = true)
    void insertRequirementData (String name, int amount);

//Select all requirements/amount info
    @Query(value = "SELECT name FROM requirement ORDER BY amount DESC", nativeQuery = true)
    ArrayList<String> selectRequirementsArray();
    @Query(value = "SELECT amount FROM requirement ORDER BY amount DESC", nativeQuery = true)
    ArrayList<Integer> selectRequirementsAmountArray();

}
