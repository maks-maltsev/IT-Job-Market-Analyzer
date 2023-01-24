package com.maltsev.vacancies_parser.service.data_manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VacancyCounterByNameTest {

    private int counter;
    private List<String> vacanciesInfo;
    private String vacancyName;
    @Before
    public void beforeTests() {

        vacanciesInfo = List.of(
                "Needed java developer",
                "Project manager vacancy",
                "Junior Java QA",
                "HR vacancy"
        );
        vacancyName = "Java";

    }

    @Test
    public void frameworksCounterShouldIncrementIfElementInListHaveFrameworkName(){

        int expectedValue = 2;

        for(int i = 0; i < vacanciesInfo.size(); i++){
            if(vacanciesInfo
                    .get(i)
                    .toLowerCase()
                    .contains(vacancyName.toLowerCase())) {
                counter++;
            }
        }
        Assert.assertEquals(expectedValue, counter);
    }
}
