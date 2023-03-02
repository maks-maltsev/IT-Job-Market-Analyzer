package com.maltsev.vacanciesparser.service.datahandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class VacancyMatchCounterTest {

    private Set<String> vacanciesSet;

    @BeforeEach
    void init() {
        vacanciesSet = new HashSet<>();
        vacanciesSet.add("Software Engineer");
        vacanciesSet.add("Senior Software Developer");
        vacanciesSet.add("Software Architect");
    }

    @Test
    void testCountAmountOfMatchesInVacanciesWithNoMatches() {
        int actual = VacancyMatchCounter.getMatchCount("Data Analyst", vacanciesSet);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void testCountAmountOfMatchesInVacanciesWithOneMatch() {
        int actual = VacancyMatchCounter.getMatchCount("developer", vacanciesSet);
        Assertions.assertEquals(1, actual);
    }

    @Test
    void testCountAmountOfMatchesInVacanciesWithMultipleMatches() {
        int actual = VacancyMatchCounter.getMatchCount("software", vacanciesSet);
        Assertions.assertEquals(3, actual);
    }
}
