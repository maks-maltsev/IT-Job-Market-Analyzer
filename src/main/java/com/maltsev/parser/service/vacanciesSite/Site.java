package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.*;

@Setter
@Getter
@ToString
public abstract class Site {

   public abstract Set<String> selectVacanciesTitles(String siteLink) throws IOException;
   public abstract Set<String> selectDescriptions(String siteLink) throws IOException, InterruptedException;
   private String siteName;

   public int vacanciesCounter(String languageName, Set<String> vacanciesSet){
        int counter = 0;
        List<String> vacanciesList = new ArrayList<>();
        vacanciesList.addAll(vacanciesSet);
        for(int i = 0; i < vacanciesList.size(); i++){
            if(vacanciesList.get(i).toLowerCase().contains(languageName + " ") || vacanciesList.get(i).toLowerCase().contains(languageName + "-")
                    || vacanciesList.get(i).toLowerCase().contains(languageName + ",") || vacanciesList.get(i).toLowerCase().contains(languageName + ".")){
                counter++;
            }
        }
       System.out.println(languageName + ": " + counter);
        return counter;
    }

    public int frameworksCounter(String frameworkName, Set<String> descriptionsSet){
        int counter = 0;
        List<String> descriptionsList = new ArrayList<>();
        descriptionsList.addAll(descriptionsSet);
        for(int i = 0; i < descriptionsList.size(); i++){
            if(descriptionsList.get(i).toLowerCase().contains(frameworkName)){
                counter++;
            }
        }
        System.out.println(frameworkName + " = " + counter);
        return counter;
    }
}
