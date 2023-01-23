package com.maltsev.parser.service.parser;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;


public interface Parser extends Callable<Set<String>> {

    Set<String> getAllVacanciesDescriptions() throws IOException, InterruptedException;
    Set<String> getAllVacanciesTitles() throws IOException;

    @Override
    default Set<String> call() throws Exception {
        Set<String> vacanciesTitlesAndDescriptionsSet = new HashSet<>();
        vacanciesTitlesAndDescriptionsSet.addAll(getAllVacanciesTitles());
        vacanciesTitlesAndDescriptionsSet.addAll(getAllVacanciesDescriptions());
        return vacanciesTitlesAndDescriptionsSet;
    }

}
