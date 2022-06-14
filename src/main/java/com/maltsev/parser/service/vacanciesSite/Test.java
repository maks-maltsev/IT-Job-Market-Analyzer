package com.maltsev.parser.service.vacanciesSite;

import com.maltsev.parser.service.dataCounters.FrameworksCounterByName;
import com.maltsev.parser.service.dataCounters.VacanciesCounterByName;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class Test {
    public static void main(String[] args) {
        DjinniCo workUa = new DjinniCo();
        Set<String> x = new HashSet<>();
        try {
            x.addAll(workUa.selectDescriptions(workUa.getSiteLink()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FrameworksCounterByName.frameworksCounter("CodeIgniter", x);
        FrameworksCounterByName.frameworksCounter("Cake PHP", x);
        FrameworksCounterByName.frameworksCounter("Grails", x);
        FrameworksCounterByName.frameworksCounter("Vaadin", x);
        FrameworksCounterByName.frameworksCounter("LibGDX", x);
        FrameworksCounterByName.frameworksCounter("Skeleton", x);
        FrameworksCounterByName.frameworksCounter("Kube", x);
        FrameworksCounterByName.frameworksCounter("Picnic CSS", x);
        FrameworksCounterByName.frameworksCounter("Meteor", x);
        FrameworksCounterByName.frameworksCounter("Koa", x);
        FrameworksCounterByName.frameworksCounter("Phoenix", x);
        FrameworksCounterByName.frameworksCounter("Ember", x);
        FrameworksCounterByName.frameworksCounter("Aurelia", x);
        FrameworksCounterByName.frameworksCounter("Web2py", x);
        FrameworksCounterByName.frameworksCounter("Pyramid", x);
        FrameworksCounterByName.frameworksCounter("Bottle", x);
        FrameworksCounterByName.frameworksCounter("Tornado", x);
        FrameworksCounterByName.frameworksCounter("CherryPy", x);

        System.out.println(x.size());
    }
}
