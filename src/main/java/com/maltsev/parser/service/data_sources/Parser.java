package com.maltsev.parser.service.data_sources;

import com.maltsev.parser.service.data_sources.impl.AbstractSite;
import com.maltsev.parser.service.data_sources.impl.DjinniCo;
import com.maltsev.parser.service.data_sources.impl.JobsUa;
import com.maltsev.parser.service.data_sources.impl.WorkUa;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Parser implements Callable<Set<String>> {
    private AbstractSite abstractSite;

    public Parser(AbstractSite abstractSite){
        this.abstractSite = abstractSite;
    }

    @Override
    public Set<String> call() throws Exception {
        return abstractSite.selectDescriptions(abstractSite.getSiteLink());
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
