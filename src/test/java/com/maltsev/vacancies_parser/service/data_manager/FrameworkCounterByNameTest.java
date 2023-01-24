package com.maltsev.vacancies_parser.service.data_manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FrameworkCounterByNameTest {

    private int counter;
    private List<String> descriptionsList;
    private String frameworkName;

    @Before
    public void beforeTests() {

        descriptionsList = List.of(
                "Needed java developer",
                "Project manager vacancy",
                "Junior Java QA",
                "HR vacancy"
                );
        frameworkName = "Java";

    }

    @Test
    public void frameworksCounterShouldIncrementIfElementInListHaveFrameworkName(){

        int expectedValue = 2;

        for(int i = 0; i < descriptionsList.size(); i++){
            if(descriptionsList
                    .get(i)
                    .toLowerCase()
                    .contains(frameworkName.toLowerCase())) {
                counter++;
            }
        }
        Assert.assertEquals(expectedValue, counter);
    }

}
