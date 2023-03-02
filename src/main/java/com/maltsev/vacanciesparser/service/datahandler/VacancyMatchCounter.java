package com.maltsev.vacanciesparser.service.datahandler;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for counting the number of times a given name appears in a set of vacancies.
 */
@Service
public class VacancyMatchCounter {

    /**
     * Counts the number of times a given name appears in a set of vacancies.
     *
     * @param name the name to search for
     * @param vacanciesSet the set of vacancies to search within
     * @return the number of times the given name appears in the set of vacancies
     */
    public static int getMatchCount(String name, Set<String> vacanciesSet) {
        int counter = 0;
        List<String> vacanciesList = new ArrayList<>(vacanciesSet);

        for (String vacancy : vacanciesList) {
            Pattern namePattern = Pattern.compile(".*" + name.toLowerCase() + ".*");
            Matcher matcher = namePattern.matcher(vacancy.toLowerCase());

            while (matcher.find()) {
                counter++;
            }
        }

        return counter;
    }

}