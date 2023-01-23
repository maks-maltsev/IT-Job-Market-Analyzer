package com.maltsev.parser.service.parser;

import com.maltsev.parser.service.parser.impl.DjinniCoParser;
import com.maltsev.parser.service.parser.impl.JobsUaParser;
import com.maltsev.parser.service.parser.impl.WorkUaParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

public class ParserImplTest {

    Parser workUaParser;
    Parser djinniCoParser;
    Parser jobsUaParser;
    Set<String> titlesSet;

    @Before
    public void init() {
        workUaParser = new WorkUaParser();
        titlesSet = new HashSet<>();
        djinniCoParser = new DjinniCoParser();
        jobsUaParser = new JobsUaParser();
    }

    @Test
    public void parserShouldReturnListOfSets() throws Exception {
        Set<String> titles = jobsUaParser.getAllVacanciesTitles();
        System.out.println("Titles: \n" + titles);
        Set<String> descriptions = jobsUaParser.getAllVacanciesDescriptions();
        System.out.println("Descriptions: \n" + descriptions);

    }

    @Test
    public void tryParseTitlesFromWorkUa() throws IOException {
        titlesSet.addAll(workUaParser.getAllVacanciesTitles());
        System.out.println(titlesSet);
    }

    @Test
    public void tryParseTitlesFromDjinniCo() throws IOException {
        System.out.println("Start parse from djinniCo");
        titlesSet.addAll(djinniCoParser.getAllVacanciesTitles());
        System.out.println("DjinniCo set: ");
        System.out.println(titlesSet);
    }

    @Test
    public void tryParseTitlesFromJobsUa() throws IOException {
        System.out.println("Start parse from jobsua");
        titlesSet.addAll(jobsUaParser.getAllVacanciesTitles());
        System.out.println("jobs.ua set: ");
        System.out.println(titlesSet);
    }

    @Test
    public void tryToParserFromAllSitesTogether() throws IOException {
        tryParseTitlesFromDjinniCo();
        tryParseTitlesFromJobsUa();
        tryParseTitlesFromWorkUa();
        System.out.println(titlesSet);
    }
}
