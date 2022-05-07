package com.maltsev.parser.repository;

import com.maltsev.parser.model.Languages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepos extends CrudRepository<Languages, Long> {

    @Query(value = "INSERT INTO language (name, amount) VALUES (?1, ?2)", nativeQuery = true)
    void insertLanguageData (String name, int amount);


}
