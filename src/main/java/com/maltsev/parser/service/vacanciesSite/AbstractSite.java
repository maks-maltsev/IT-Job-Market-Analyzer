package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@Setter
@Getter
@ToString
public abstract class AbstractSite implements Callable<Set<String>> {

   public abstract Set<String> selectVacanciesTitles(String siteLink) throws IOException;
   public abstract Set<String> selectDescriptions(String siteLink) throws IOException, InterruptedException;
   private String siteLink;
   private String siteName;

   public int languagesCounter(String languageName, Set<String> vacanciesSet){
        int counter = 0;
        List<String> vacanciesList = new ArrayList<>();
        vacanciesList.addAll(vacanciesSet);
        for(int i = 0; i < vacanciesList.size(); i++){
            if(vacanciesList.get(i).toLowerCase().contains(" " + languageName + " ") || vacanciesList.get(i).toLowerCase().contains(" " + languageName + "-")
                    || vacanciesList.get(i).toLowerCase().contains(" " + languageName + ",") || vacanciesList.get(i).toLowerCase().contains(" " + languageName + ".")){
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

    @Override
    public Set<String> call() throws Exception {
        return selectDescriptions(getSiteLink());
    }

    public Set<String> returnAllDescriptions() throws ExecutionException, InterruptedException {
        Set<String> allDescriptions = new HashSet<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Set<String>> futureWorkUa = executorService.submit(()->{
            return new WorkUa().call();
        });
        Future<Set<String>> futureDjinniCo = executorService.submit(() ->{
            return new DjinniCo().call();
        });
        Future<Set<String>> futureJobsUa = executorService.submit(() -> {
            return new JobsUa().call();
        });
        executorService.shutdown();
        executorService.shutdown();
        executorService.shutdown();

        allDescriptions.addAll(futureWorkUa.get());
        allDescriptions.addAll(futureDjinniCo.get());
        allDescriptions.addAll(futureJobsUa.get());

        return allDescriptions;
    }

    public void refreshLanguageTable() throws ExecutionException, InterruptedException {
       List<String> allDescriptionsList = new ArrayList<>();
       allDescriptionsList.addAll(returnAllDescriptions());
    }
}