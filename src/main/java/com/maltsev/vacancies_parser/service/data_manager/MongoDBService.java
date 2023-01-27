package com.maltsev.vacancies_parser.service.data_manager;

import com.maltsev.vacancies_parser.entity.Framework;
import com.maltsev.vacancies_parser.entity.Requirement;
import com.maltsev.vacancies_parser.entity.Vacancy;
import com.maltsev.vacancies_parser.entity.enums.*;
import com.maltsev.vacancies_parser.repository.FrameworkRepository;
import com.maltsev.vacancies_parser.repository.RequirementRepository;
import com.maltsev.vacancies_parser.repository.VacancyRepository;
import com.maltsev.vacancies_parser.service.date_manager.DateService;
import com.maltsev.vacancies_parser.service.parser.Parser;
import com.maltsev.vacancies_parser.service.parser.impl.DjinniCoParser;
import com.maltsev.vacancies_parser.service.parser.impl.JobsUaParser;
import com.maltsev.vacancies_parser.service.parser.impl.WorkUaParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static com.maltsev.vacancies_parser.service.data_manager.MatchesInVacanciesCounter.countAmountOfMatchesInVacancies;

@Service
@EnableScheduling
public class MongoDBService {

    @Autowired
    private FrameworkRepository frameworkRepository;
    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private VacancyRepository vacancyRepository;

    private static Set<String> vacanciesInfo;
    private static List<Parser> parsers;
    private List<Future<Set<String>>> futures;
    private final String[] frameworks;
    private final String[] vacancies;
    private final String[] requirements;

    @Autowired
    public MongoDBService(WorkUaParser workUaParser,
                          JobsUaParser jobsUaParser,
                          DjinniCoParser djinniCoParser) {
        vacanciesInfo = new HashSet<>();
        futures = new ArrayList<>();

        frameworks = Frameworks.getFrameworksArray();
        vacancies = Vacancies.getVacanciesArray();
        requirements = Requirements.getRequirementsArray();

        parsers = List.of(
                workUaParser,
                jobsUaParser,
                djinniCoParser
        );
    }

    @Scheduled(cron = "0 1 0 1 * *")
    @PostConstruct
    private void insertNewDataIntoDB() throws Exception {

        if(vacanciesInfo.isEmpty()) {
            getAllVacanciesData();
        }
        insertFrameworksIntoDB();
        insertRequirementsIntoDB();
        insertVacanciesIntoDB();

        vacanciesInfo.clear();

    }

    private void insertFrameworksIntoDB() {

        for(int i = 0; i < frameworks.length; i++){
            Framework framework = new Framework(
                    frameworks[i],
                    countAmountOfMatchesInVacancies(frameworks[i], vacanciesInfo),
                    DateService.getFormattedDate()
            );
            frameworkRepository.save(framework);
        }

    }

    private void insertRequirementsIntoDB() {

        for (int i = 0; i < requirements.length; i++){
            Requirement requirement = new Requirement(
                    RequirementsForChart.getAllRequirementsForChart()[i],
                    countAmountOfMatchesInVacancies(requirements[i], vacanciesInfo),
                    DateService.getFormattedDate()
            );
            requirementRepository.save(requirement);
        }

    }

    private void insertVacanciesIntoDB() {

        for (int i = 0; i < vacancies.length; i++){
            Vacancy vacancy = new Vacancy(
                    VacanciesForChart.getAllVacanciesForChart()[i],
                    countAmountOfMatchesInVacancies(vacancies[i], vacanciesInfo),
                    DateService.getFormattedDate()
            );
            vacancyRepository.save(vacancy);
        }

    }

    public void getAllVacanciesData() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(parsers.size());
        for(int i = 0; i < parsers.size(); i++) {
            futures.add(executorService.submit(parsers.get(i)));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        addVacanciesDataToSet(futures);

    }

    private void addVacanciesDataToSet(List<Future<Set<String>>> vacanciesFutures)
            throws ExecutionException, InterruptedException {

       for (Future<Set<String>> future : vacanciesFutures) {
           vacanciesInfo.addAll(future.get());
       }

    }

}
