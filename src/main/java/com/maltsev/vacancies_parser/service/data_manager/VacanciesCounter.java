package com.maltsev.vacancies_parser.service.data_manager;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VacanciesCounter {
    public static int vacanciesCounter(String languageName,
                                       Set<String> vacanciesSet) {

        int counter = 0;
        List<String> vacanciesList = new ArrayList<>(vacanciesSet);
        for(int i = 0; i < vacanciesList.size(); i++){
            Pattern namePattern = Pattern
                    .compile(" " + languageName.toLowerCase() + "[\\s-,.=/']");
            Matcher matcher = namePattern
                    .matcher(vacanciesList
                            .get(i)
                            .toLowerCase());
            while (matcher.find()){
                counter++;
            }
        }
        return counter;

    }

}
