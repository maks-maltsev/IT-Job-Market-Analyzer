package com.maltsev.parser.service.vacanciesSite;

import com.maltsev.parser.service.frameworks.IFrameworks;
import com.maltsev.parser.service.programmingLanguage.ILanguages;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        WorkUa workUa = new WorkUa();
        JobsUa jobsUa = new JobsUa();
        DjinniCo djinniCo = new DjinniCo();
        String [] frameworks = IFrameworks.frameworks;
        String [] languages = ILanguages.languages;

        workUa.selectDescriptions(workUa.getSiteLink());
        djinniCo.selectDescriptions(djinniCo.getSiteLink());
        jobsUa.selectDescriptions(jobsUa.getSiteLink());

        for(int j = 0; j < frameworks.length; j++){
            jobsUa.frameworksCounter(frameworks[j], jobsUa.getVacanciesDescriptionsSet());
        }
        System.out.println(" ");
        for(int j = 0; j < frameworks.length; j++){
            workUa.frameworksCounter(frameworks[j], workUa.getVacanciesDescriptionsSet());
        }
        System.out.println(" ");
        for(int j = 0; j < frameworks.length; j++){
            djinniCo.frameworksCounter(frameworks[j], djinniCo.getVacanciesDescriptionsSet());
        }

    }

}
