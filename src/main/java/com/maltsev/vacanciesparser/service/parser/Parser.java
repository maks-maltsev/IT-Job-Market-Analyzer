package com.maltsev.vacanciesparser.service.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 *Interface representing a parser that extracts vacancies data from a job search website.
 */
public interface Parser extends Callable<Set<String>> {

    Set<String> getAllVacanciesDescriptions() throws IOException, InterruptedException;
    Set<String> getAllVacanciesTitles() throws IOException;

    @Override
    default Set<String> call() throws Exception {
        Set<String> vacanciesDetails = new HashSet<>();
        vacanciesDetails.addAll(getAllVacanciesTitles());
        vacanciesDetails.addAll(getAllVacanciesDescriptions());
        return vacanciesDetails;
    }

}