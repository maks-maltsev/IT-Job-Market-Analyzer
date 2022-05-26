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

    @Override
    public Set<String> call() throws Exception {
        return selectDescriptions(getSiteLink());
    }

    public Set<String> returnAllDescriptions() throws ExecutionException, InterruptedException {
        Set<String> allDescriptions = new HashSet<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Set<String>> futureWorkUa = executorService.submit(()-> new WorkUa().call());
        Future<Set<String>> futureDjinniCo = executorService.submit(() -> new DjinniCo().call());
        Future<Set<String>> futureJobsUa = executorService.submit(() ->  new JobsUa().call());
        executorService.shutdown();
        executorService.shutdown();
        executorService.shutdown();

        allDescriptions.addAll(futureWorkUa.get());
        allDescriptions.addAll(futureDjinniCo.get());
        allDescriptions.addAll(futureJobsUa.get());

        return allDescriptions;
    }
}