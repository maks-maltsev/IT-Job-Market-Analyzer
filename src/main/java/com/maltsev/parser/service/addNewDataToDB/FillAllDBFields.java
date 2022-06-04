package com.maltsev.parser.service.addNewDataToDB;

import com.maltsev.parser.model.Frameworks;
import com.maltsev.parser.model.Vacancies;
import com.maltsev.parser.model.Requirements;
import com.maltsev.parser.repository.FrameworksRepos;
import com.maltsev.parser.repository.VacanciesRepos;
import com.maltsev.parser.repository.RequirementsRepos;
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
    FrameworksRepos frameworksRepos;
    @Autowired
    VacanciesRepos vacanciesRepos;
    @Autowired
    RequirementsRepos requirementsRepos;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @Scheduled(cron = "0 1 0 1 * *")
    public void runAfterObjectCreated() throws ExecutionException, InterruptedException {
        Set<String> allDescriptionsSet = new HashSet<>();
        allDescriptionsSet.addAll(new WorkUa().returnAllDescriptions());

        String [] frameworks = IFrameworks.frameworks;
        String [] languages = IVacancies.vacancies;
        String [] requirements = IRequirements.requirements;

        for(int i = 0; i < frameworks.length; i++){
            Frameworks frameworks1 = new Frameworks(frameworks[i], frameworksCounter(frameworks[i], allDescriptionsSet), formatter.format(date));
            frameworksRepos.save(frameworks1);
        }

        for (int i = 0; i < languages.length; i++){
            Vacancies vacancies1 = new Vacancies(languages[i], vacanciesCounter(languages[i], allDescriptionsSet), formatter.format(date));
            vacanciesRepos.save(vacancies1);
        }

        for (int i = 0; i < requirements.length; i++){
            Requirements requirements1 = new Requirements(requirements[i], frameworksCounter(requirements[i], allDescriptionsSet), formatter.format(date));
            requirementsRepos.save(requirements1);
        }
    }

}