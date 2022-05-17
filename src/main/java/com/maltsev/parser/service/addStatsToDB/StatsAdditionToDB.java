package com.maltsev.parser.service.addStatsToDB;

import com.maltsev.parser.model.Frameworks;
import com.maltsev.parser.model.Languages;
import com.maltsev.parser.model.Requirements;
import com.maltsev.parser.repository.FrameworksRepos;
import com.maltsev.parser.repository.LanguageRepos;
import com.maltsev.parser.repository.RequirementsRepos;
import com.maltsev.parser.service.frameworks.iFrameworks;
import com.maltsev.parser.service.programmingLanguage.iLanguages;
import com.maltsev.parser.service.requirements.iRequirements;
import com.maltsev.parser.service.vacanciesSite.WorkUa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@EnableScheduling
public class StatsAdditionToDB {
    @Autowired
    FrameworksRepos frameworksRepos;
    @Autowired
    LanguageRepos languageRepos;
    @Autowired
    RequirementsRepos requirementsRepos;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());
    @Scheduled(cron = "0 1 0 1 * *")
    public void runAfterObjectCreated() throws ExecutionException, InterruptedException {
        Set<String> allDescriptionsSet = new HashSet<>();
        allDescriptionsSet.addAll(new WorkUa().returnAllDescriptions());

        String [] frameworks = iFrameworks.frameworks;
        String [] languages = iLanguages.languages;
        String [] requirements = iRequirements.requirements;

        for(int i = 0; i < frameworks.length; i++){
            Frameworks frameworks1 = new Frameworks(frameworks[i], new WorkUa().frameworksCounter(frameworks[i], allDescriptionsSet), formatter.format(date));
            frameworksRepos.save(frameworks1);
        }

        for (int i = 0; i < languages.length; i++){
            Languages languages1 = new Languages(languages[i], new WorkUa().languagesCounter(languages[i], allDescriptionsSet), formatter.format(date));
            languageRepos.save(languages1);
        }

        for (int i = 0; i < requirements.length; i++){
            Requirements requirements1 = new Requirements(requirements[i], new WorkUa().frameworksCounter(requirements[i], allDescriptionsSet), formatter.format(date));
            requirementsRepos.save(requirements1);
        }
    }
}