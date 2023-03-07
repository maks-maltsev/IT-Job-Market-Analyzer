package com.maltsev.vacanciesparser.service.parser.impl;

import com.maltsev.vacanciesparser.service.datasources.impl.WorkUa;
import com.maltsev.vacanciesparser.service.parser.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class WorkUaParser implements Parser {

    private static final Logger logger = LoggerFactory.getLogger(WorkUaParser.class);
    private final WorkUa workUa;

    @Autowired
    public WorkUaParser(WorkUa workUa) {
        this.workUa = workUa;
    }

    private void sleepOneMillisecond() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.error("An error occurred while work.ua tread sleep", e);
        }
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() {
        logger.info("Invoke WorkUaParser.getAllVacanciesDescription().");
        Set<String> vacanciesDescriptionsSet = new HashSet<>();

        for(int i = 1; i <= workUa.getPages(); i++) {
            sleepOneMillisecond();
            Document linkDoc = null;
            try {
                linkDoc = Jsoup.connect(workUa.getVacanciesPageUrl() + i).get();
            } catch (IOException e) {
                logger.error("An error occurred while parser trying to connect to work.ua page " + i
                        + ". getAllVacanciesDescriptions() method.", e);
            }
            assert linkDoc != null;
            Elements detailsLink = linkDoc.select("h2[class='']");
            if (!detailsLink.isEmpty()) {
                detailsLink.forEach(link -> {
                    sleepOneMillisecond();
                    String vacancyLink = Objects.requireNonNull(link.selectFirst("a")).attr("href");
                    try {
                        Document descriptionDoc = Jsoup
                                .connect(workUa.getUrl() + vacancyLink)
                                .get();
                        vacanciesDescriptionsSet
                                .add(descriptionDoc
                                        .getElementsByAttributeValue("id", "job-description")
                                        .text());
                    } catch (IOException e) {
                        logger.error("An error occurred while trying to connect to vacancy description from work.ua", e);
                    }
                });
            } else {
                logger.warn("No vacancy links found on page " + i + ". getAllVacanciesDescriptions() method.");
            }
        }
        logger.info("WorkUaParser.getAllVacanciesDescription() returns " + vacanciesDescriptionsSet.size() + " vacancy descriptions.");

        return vacanciesDescriptionsSet;
    }

    @Override
    public Set<String> getAllVacanciesTitles() {
        logger.info("Invoke WorkUaParser.getAllVacanciesTitles()");
        Set<String> vacanciesTitlesSet = new HashSet<>();

        for(int i = 1; i <= workUa.getPages(); i++) {
            Document titlesDoc = null;
            try {
                titlesDoc = Jsoup.connect(workUa.getVacanciesPageUrl() + i).get();
            } catch (IOException e) {
                logger.error("An exception occurred while connecting to the work.ua vacancies page " + i
                        + ". getAllVacanciesTitles() method.", e);
            }
            assert titlesDoc != null;
            Elements vacanciesTitles = titlesDoc.select("h2[class='']");
            if (!vacanciesTitles.isEmpty()) {
                vacanciesTitles.forEach(tit -> {
                    Element title = tit.child(0);
                    vacanciesTitlesSet.add(title.text());
                });
            } else {
                logger.warn("No vacancy titles found on page " + i + ". getAllVacanciesDescriptions() method.");
            }
        }
        logger.info("WorkUaParser returns " + vacanciesTitlesSet.size() + " vacancy titles.");

        return vacanciesTitlesSet;
    }

}