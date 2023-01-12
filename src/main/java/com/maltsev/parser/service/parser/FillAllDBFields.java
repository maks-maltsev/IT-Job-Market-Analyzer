package com.maltsev.parser.service.parser;

import com.maltsev.parser.entity.Framework;
import com.maltsev.parser.entity.Vacancy;
import com.maltsev.parser.entity.Requirement;
import com.maltsev.parser.entity.emuns.*;
import com.maltsev.parser.repository.FrameworkRepository;
import com.maltsev.parser.repository.VacancyRepository;
import com.maltsev.parser.repository.RequirementRepository;
import com.maltsev.parser.service.date_manager.DateService;

import com.maltsev.parser.service.data_sources.impl.WorkUa;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static com.maltsev.parser.service.date_manager.DateService.*;
import static com.maltsev.parser.service.data_manager.FrameworksCounterByName.frameworksCounter;
import static com.maltsev.parser.service.data_manager.VacanciesCounterByName.vacanciesCounter;

@EnableScheduling
public class FillAllDBFields {

    private final FrameworkRepository frameworkRepository;
    private final VacancyRepository vacancyRepository;
    private final RequirementRepository requirementRepository;

    public FillAllDBFields(FrameworkRepository frameworkRepository, VacancyRepository vacancyRepository, RequirementRepository requirementRepository) {
        this.frameworkRepository = frameworkRepository;
        this.vacancyRepository = vacancyRepository;
        this.requirementRepository = requirementRepository;
    }

    @Scheduled(cron = "0 1 0 1 * *")
    public void runAfterObjectCreated() throws ExecutionException, InterruptedException {
        Set<String> allDescriptionsSet = new HashSet<>();
        allDescriptionsSet.addAll(new WorkUa().returnAllDescriptions());

        String[] frameworks = Frameworks.getFrameworksArray();
        String[] vacancies = Vacancies.getVacanciesArray();
        String[] vacanciesForChart = VacanciesForChart.getAllVacanciesForChart();
        String[] requirements = Requirements.getRequirementsArray();
        String[] requirementsForChart = RequirementsForChart.getAllRequirementsForChart();

        for(int i = 0; i < frameworks.length; i++){
            Framework framework1 = new Framework(frameworks[i], frameworksCounter(frameworks[i], allDescriptionsSet), DateService.formatter.format(date));
            frameworkRepository.save(framework1);
        }

        for (int i = 0; i < vacancies.length; i++){
            Vacancy vacancy1 = new Vacancy(vacanciesForChart[i], vacanciesCounter(vacancies[i], allDescriptionsSet), formatter.format(date));
            vacancyRepository.save(vacancy1);
        }

        for (int i = 0; i < requirements.length; i++){
            Requirement requirement1 = new Requirement(requirementsForChart[i], frameworksCounter(requirements[i], allDescriptionsSet), formatter.format(date));
            requirementRepository.save(requirement1);
        }
    }

}