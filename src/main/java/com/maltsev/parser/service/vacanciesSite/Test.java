package com.maltsev.parser.service.vacanciesSite;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.maltsev.parser.service.programmingLanguage.iLanguages;
import com.maltsev.parser.service.requirements.iRequirements;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        WorkUa workUa = new WorkUa();
        JobsUa jobsUa = new JobsUa();
        DjinniCo djinniCo = new DjinniCo();

        Set<String> descriptions = new HashSet<>();

        descriptions.addAll(workUa.selectDescriptions(workUa.getSiteLink()));
        descriptions.addAll(jobsUa.selectDescriptions(jobsUa.getSiteLink()));
        descriptions.addAll(djinniCo.selectDescriptions(djinniCo.getSiteLink()));

        System.out.println("Description array size = " + descriptions.size());
        String [] languages = iLanguages.languages;

        System.out.println(" ");

        int [] s = new int[languages.length];
        for(int j = 0; j < languages.length; j++){
            s[j] = workUa.vacanciesCounter(languages[j], descriptions);
        }
        int total = 0;

        for(int j = 0; j < s.length; j++){
            total+=s[j];
        }

        for (int i = 0; i < s.length-1; i++){
            for (int j = 0; j < s.length-1; j++) {
                if (s[j + 1] > s[j]) {
                    int x = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = x;

                    String str = languages[j];
                    languages[j] = languages[j+1];
                    languages[j+1] = str;
                }
            }
        }

        System.out.println("");

        System.out.println("Total vacancies find: " + total);
        DecimalFormat df = new DecimalFormat("###.##");
        for (int j = 0; j < languages.length; j++){
            double x = s[j];
            x = (x /total) * 100;
            System.out.println(languages[j] + " = " + df.format(x) + "%");
        }

    }
}
