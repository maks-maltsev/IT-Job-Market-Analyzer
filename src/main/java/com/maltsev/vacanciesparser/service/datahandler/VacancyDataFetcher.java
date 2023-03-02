package com.maltsev.vacanciesparser.service.datahandler;

import com.maltsev.vacanciesparser.service.parser.Parser;
import com.maltsev.vacanciesparser.service.parser.impl.DjinniCoParser;
import com.maltsev.vacanciesparser.service.parser.impl.JobsUaParser;
import com.maltsev.vacanciesparser.service.parser.impl.WorkUaParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * The VacancyDataFetcher class is responsible for fetching all vacancies from different websites using parsers.
 * It creates instances of parsers and runs them in parallel to get vacancies data. Then, it adds the results to the set of vacancies.
 */
@Service
public class VacancyDataFetcher {

    private final List<Parser> parsers;
    private final Set<String> vacanciesSet;

    @Autowired
    public VacancyDataFetcher(WorkUaParser workUaParser,
                              JobsUaParser jobsUaParser,
                              DjinniCoParser djinniCoParser) {
        this.vacanciesSet = new HashSet<>();
        this.parsers = List.of(
                workUaParser,
                jobsUaParser,
                djinniCoParser
        );
    }

    public void getAllVacanciesData() throws Exception {
        vacanciesSet.clear();
        ExecutorService executorService = Executors.newFixedThreadPool(parsers.size());

        List<Future<Set<String>>> futures = parsers.stream()
                .map(executorService::submit)
                .collect(Collectors.toList());

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        addVacanciesDataToSet(futures);
    }

    private void addVacanciesDataToSet(List<Future<Set<String>>> vacanciesFutures) throws Exception {
        vacanciesSet.clear();
        for (Future<Set<String>> future : vacanciesFutures) {
            vacanciesSet.addAll(future.get());
        }
    }

    public Set<String> getVacanciesSet() {
        return vacanciesSet;
    }

}