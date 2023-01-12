package com.maltsev.parser.service.data_manager;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancyCounterByNameTest {

    @Test
    public void vacanciesCounterShouldIncrementIfElementInListHaveVacancyName(){
        int counter = 0;
        int expValue = 2;
        String languageName = "java";
        Set<String> vacanciesSet = new HashSet<>();
        vacanciesSet.add("Needed java developer");         // 1
        vacanciesSet.add("Project manager vacancy");
        vacanciesSet.add("Junior Java QA");                 //2
        vacanciesSet.add("HR vacancy");
        vacanciesSet.add("Junior Java QA");                 //3 не будет занесена, т.к. структура данных - сэт
        String frameworkName = "java";

        List<String> vacanciesList = new ArrayList<>();
        vacanciesList.addAll(vacanciesSet);
        System.out.println(vacanciesList);
        for(int i = 0; i < vacanciesList.size(); i++){
            Pattern namePattern = Pattern.compile(" " + languageName + "[\\s-,.=]");
            Matcher matcher = namePattern.matcher(vacanciesList.get(i).toLowerCase());
            while (matcher.find()){
                counter++;
            }
        }
        Assert.assertEquals(expValue, counter);

    }
}
