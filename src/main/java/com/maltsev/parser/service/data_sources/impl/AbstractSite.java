package com.maltsev.parser.service.data_sources.impl;

import com.maltsev.parser.service.data_sources.Site;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import java.util.concurrent.*;

@Setter
@Getter
public abstract class AbstractSite implements Callable<Set<String>>, Site {
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