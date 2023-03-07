package com.maltsev.vacanciesparser.service.datahandler;

import org.springframework.stereotype.Service;

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
        Pattern namePattern = Pattern.compile(name.toLowerCase() + "\\P{L}");
        Matcher matcher;

        for (String vacancy : vacanciesSet) {
            matcher = namePattern.matcher(vacancy.toLowerCase());
            if (matcher.find()) {
                counter++;
            }
        }

        return counter;
    }

}