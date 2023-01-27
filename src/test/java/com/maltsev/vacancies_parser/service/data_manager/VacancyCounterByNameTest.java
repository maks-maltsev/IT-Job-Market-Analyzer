package com.maltsev.vacancies_parser.service.data_manager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VacancyCounterByNameTest {

    private int counter;
    private Set<String> vacanciesInfo;
    private String vacancyName;


    @Before
    public void beforeTests() {

        vacanciesInfo = Set.of(
                "Needed java/developer",
                "Project manager vacancy",
                "Junior ,Java, QA",
                "HR vacancy",
                "Ability to develop(laravel)"
        );
        vacancyName = "Java";

    }

    @Test
    public void frameworksCounterShouldIncrementIfElementInListHaveFrameworkName(){

        int amount = MatchesInVacanciesCounter.countAmountOfMatchesInVacancies("Laravel", vacanciesInfo);
        assertEquals(1, amount);
    }
}
