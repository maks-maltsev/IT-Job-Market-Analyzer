package com.maltsev.parser.service.addNewDataToDB;

import com.maltsev.parser.entity.Framework;
import com.maltsev.parser.entity.Vacancy;
import com.maltsev.parser.entity.Requirement;
import com.maltsev.parser.repository.FrameworkRepository;
import com.maltsev.parser.repository.VacancyRepository;
import com.maltsev.parser.repository.RequirementRepository;
import com.maltsev.parser.service.dataEnums.IFrameworks;
import com.maltsev.parser.service.dataEnums.IVacancies;
import com.maltsev.parser.service.dataEnums.IRequirements;
import com.maltsev.parser.service.vacanciesSite.WorkUa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static com.maltsev.parser.service.dataCounters.FrameworksCounterByName.frameworksCounter;
import static com.maltsev.parser.service.dataCounters.VacanciesCounterByName.vacanciesCounter;

@EnableScheduling
public class FillAllDBFields {
    @Autowired
    FrameworkRepository frameworkRepository;
    @Autowired
    VacancyRepository vacancyRepository;
    @Autowired
    RequirementRepository requirementRepository;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @Scheduled(cron = "0 1 0 1 * *")
    public void runAfterObjectCreated() throws ExecutionException, InterruptedException {
        Set<String> allDescriptionsSet = new HashSet<>();
        allDescriptionsSet.addAll(new WorkUa().returnAllDescriptions());

        String [] frameworks = IFrameworks.frameworks;
        String [] vacancies = IVacancies.vacancies;
        String [] vacanciesForChart = IVacancies.vacanciesForChart;
        String [] requirements = IRequirements.requirements;
        String [] requirementsForChart = IRequirements.requirementsForChart;

        for(int i = 0; i < frameworks.length; i++){
            Framework framework1 = new Framework(frameworks[i], frameworksCounter(frameworks[i], allDescriptionsSet), "2022-05");
            frameworkRepository.save(framework1);
        }

        for (int i = 0; i < vacancies.length; i++){
            Vacancy vacancy1 = new Vacancy(vacanciesForChart[i], vacanciesCounter(vacancies[i], allDescriptionsSet), "2022-05");
            vacancyRepository.save(vacancy1);
        }

        for (int i = 0; i < requirements.length; i++){
            Requirement requirement1 = new Requirement(requirementsForChart[i], frameworksCounter(requirements[i], allDescriptionsSet), "2022-05");
            requirementRepository.save(requirement1);
        }
    }

}