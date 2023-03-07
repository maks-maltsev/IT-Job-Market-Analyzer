package com.maltsev.vacanciesparser.service.parser.impl;

import com.maltsev.vacanciesparser.service.datasources.impl.JobsUa;
import com.maltsev.vacanciesparser.service.parser.Parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class JobsUaParser implements Parser {

    private static final Logger logger = LoggerFactory.getLogger(JobsUaParser.class);
    private final JobsUa jobsUa;

    @Autowired
    public JobsUaParser(JobsUa jobsUa) {
        this.jobsUa = jobsUa;
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() {
        logger.info("Invoke JobsUaParser.getAllVacanciesDescription()");
        Set<String> vacanciesDescriptionsSet = new HashSet<>();

        for(int i = 1; i <= jobsUa.getPages(); i++) {
            Document linksDoc = null;
            try {
                linksDoc = Jsoup.connect(jobsUa.getVacanciesPageUrl() + i).get();
            } catch (IOException e) {
                logger.error("An error occurred while connecting to jobs.ua vacancies page", e);
            }
            assert linksDoc != null;
            Elements links = linksDoc.getElementsByClass("b-vacancy__top__title js-item_title");
            links.forEach(link ->{
                try {
                    Document vacancyDetails = Jsoup
                            .connect(link.attr("href"))
                            .get();
                    vacanciesDescriptionsSet
                            .add(vacancyDetails
                                    .getElementsByClass("b-vacancy-full__block b-text js-phone-replace")
                                    .text());
                } catch (IOException e) {
                    logger.error("An error occurred while getting jobs.ua vacancy details" + e);
                }
            });
        }
        logger.info("JobsUaParser.getAllVacanciesDescription() returns " + vacanciesDescriptionsSet.size() + " vacancy descriptions");

        return vacanciesDescriptionsSet;
    }

    @Override
    public Set<String> getAllVacanciesTitles() {
        logger.info("Invoke JobsUaParser.getAllVacanciesTitles()");
        Set<String> vacanciesTitlesSet = new HashSet<>();

        for (int i = 1; i <= jobsUa.getPages(); i++) {
            Document doc = null;
            try {
                doc = Jsoup.connect(jobsUa.getVacanciesPageUrl() + i).get();
            } catch (IOException e) {
                logger.error("An error occurred while connecting to the jobs.ua vacancies page", e);
            }
            assert doc != null;
            Elements vacanciesTitles = doc
                    .getElementsByClass("b-vacancy__top__title js-item_title");
            try {
                vacanciesTitles.forEach(title -> {
                    vacanciesTitlesSet.add(title.text());
                });
            } catch (IndexOutOfBoundsException e) {
                logger.error("An error occurred while iterating over jobs.ua vacancies details links", e);
            }
        }
        logger.info("JobsUaParser.getAllVacanciesTitles() returns " + vacanciesTitlesSet.size() + " vacancies titles.");

        return vacanciesTitlesSet;
    }

}
