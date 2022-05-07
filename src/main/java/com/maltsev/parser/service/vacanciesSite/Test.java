package com.maltsev.parser.service.vacanciesSite;

import com.maltsev.parser.service.frameworks.Framework;
import com.maltsev.parser.service.programmingLanguage.Language;

import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {

        WorkUa workUa = new WorkUa();
        DjinniCo djinniCo = new DjinniCo();
        JobsUa jobsUa = new JobsUa();

        Set<String> workUaSet = workUa.selectAllVacancies(workUa.getSiteLink());
        Set<String> djinnoCoSet = djinniCo.selectAllVacancies(djinniCo.getSiteLink());
        Set<String> jobsUaSet = jobsUa.selectAllVacancies(jobsUa.getSiteLink());

        Set<String> all = new HashSet<>();

        all.addAll(workUaSet);
        all.addAll(djinnoCoSet);
        all.addAll(jobsUaSet);

        System.out.println(all.size());


        Language language = new Language();
        String [] languages = language.getLanguages();
        int [] languageAmount = new int[languages.length];

        for(int j = 0; j < languages.length; j++){
            languageAmount[j] = workUa.vacanciesCounter(languages[j], all );
        }

        for(int j = 0; j < languages.length; j++){
            System.out.println(languages[j] + " = " + languageAmount[j]);
        }
        int total = 0;
        for (int i: languageAmount) {
            total+=i;
        }

        System.out.println(total);
    }

}
