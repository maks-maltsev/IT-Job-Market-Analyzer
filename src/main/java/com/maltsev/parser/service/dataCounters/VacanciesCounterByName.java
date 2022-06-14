package com.maltsev.parser.service.dataCounters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacanciesCounterByName {
    public static int vacanciesCounter(String languageName, Set<String> vacanciesSet){
        int counter = 0;
        List<String> vacanciesList = new ArrayList<>();
        vacanciesList.addAll(vacanciesSet);

        for(int i = 0; i < vacanciesList.size(); i++){
            Pattern namePattern = Pattern.compile(" " + languageName.toLowerCase() + "[\\s-,.=/']");
            Matcher matcher = namePattern.matcher(vacanciesList.get(i).toLowerCase());
            while (matcher.find()){
                counter++;
                System.out.println(vacanciesList.get((i)));
            }
        }
        System.out.println(languageName + " : " + counter);
        return counter;
    }
}
