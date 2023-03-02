package com.maltsev.vacanciesparser.service.parser;

import com.maltsev.vacanciesparser.service.datasources.impl.DjinniCo;
import com.maltsev.vacanciesparser.service.datasources.impl.JobsUa;
import com.maltsev.vacanciesparser.service.datasources.impl.WorkUa;
import com.maltsev.vacanciesparser.service.parser.impl.DjinniCoParser;
import com.maltsev.vacanciesparser.service.parser.impl.JobsUaParser;
import com.maltsev.vacanciesparser.service.parser.impl.WorkUaParser;
import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
public class ParserImplTest {

    private DjinniCoParser djinniCoParser;
    private JobsUaParser jobsUaParser;
    private WorkUaParser workUaParser;

    @Before
    public void init() {
        djinniCoParser = new DjinniCoParser(new DjinniCo());
        jobsUaParser = new JobsUaParser(new JobsUa());
        workUaParser = new WorkUaParser(new WorkUa());
    }

    @Test
    public void shouldReturnFilledSetsFromJobsUa() throws Exception {
        Set<String> titles = jobsUaParser.getAllVacanciesTitles();
        Set<String> descriptions = jobsUaParser.getAllVacanciesDescriptions();

        assertFalse(titles.isEmpty());
        assertFalse(descriptions.isEmpty());
    }

    @Test
    public void shouldReturnFilledSetsFromWorkUa() throws IOException, InterruptedException {
        Set<String> titles = workUaParser.getAllVacanciesTitles();
        Set<String> descriptions = workUaParser.getAllVacanciesDescriptions();

        assertFalse(titles.isEmpty());
        assertFalse(descriptions.isEmpty());
    }

    @Test
    public void shouldReturnFilledSetsFromDjinniCo() throws IOException {
        Set<String> titles = djinniCoParser.getAllVacanciesTitles();
        Set<String> descriptions = djinniCoParser.getAllVacanciesDescriptions();

        assertFalse(titles.isEmpty());
        assertFalse(descriptions.isEmpty());
    }


}
