package com.maltsev.vacanciesparser.service.parser.impl;

import com.maltsev.vacanciesparser.service.datasources.impl.DjinniCo;
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
public class DjinniCoParser implements Parser {

    private final DjinniCo djinniCo;
    private final Logger logger = LoggerFactory.getLogger(DjinniCoParser.class);

    @Autowired
    public DjinniCoParser(DjinniCo djinniCo) {
        this.djinniCo = djinniCo;
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() {
        logger.info("Invoke DjinniCoParser.getAllVacanciesDescription()");
        Set<String> vacanciesDescriptionsSet = new HashSet<>();

        for(int i = 1; i <= djinniCo.getPages(); i++){
            Document linkDoc = null;
            try {
                linkDoc = Jsoup.connect(djinniCo.getVacanciesPageUrl() + i).get();
            } catch (IOException e) {
                logger.error("An error occurred while connecting to the vacancies page", e);
            }
            Elements detailsLink = linkDoc.getElementsByClass("profile");
            try{
                detailsLink.forEach(link -> {
                    try {
                        Document vacancyDetails = Jsoup
                                .connect(djinniCo.getUrl() + link.attr("href"))
                                .get();
                        vacanciesDescriptionsSet
                                .add(vacancyDetails.getElementsByClass("col-sm-8 row-mobile-order-2")
                                        .text());
                    } catch (IOException e) {
                        logger.error("An error occurred while getting vacancy details", e);
                    }
                });
            } catch (IndexOutOfBoundsException e) {
                logger.error("An error occurred while iterating over vacancies details links", e);
            }
        }
        logger.info("DjinniCoParser.getAllVacanciesDescription() returns " + vacanciesDescriptionsSet.size() + " vacancies descriptions");

        return vacanciesDescriptionsSet;
    }

    @Override
    public Set<String> getAllVacanciesTitles() {
        logger.info("Invoke DjinniCo.getAllVacanciesTitles()");
        Set<String> vacanciesTitlesSet = new HashSet<>();

        for(int i = 1; i <= djinniCo.getPages(); i++) {
            Document titlesDoc = null;
            try {
                titlesDoc = Jsoup.connect(djinniCo.getVacanciesPageUrl() + i).get();
            } catch (IOException e) {
                logger.error("An error occurred while connecting to the vacancies page", e);
            }
            assert titlesDoc != null;
            Elements vacanciesTitles = titlesDoc.getElementsByClass("profile");
            try {
                vacanciesTitles.forEach(title -> {
                    vacanciesTitlesSet.add(title.text());
                });
            } catch (IndexOutOfBoundsException e) {
                logger.error("An error occurred while iterating over vacancies titles links", e);
            }
        }
        logger.info("DjinniCo.getAllVacanciesTitles() returns " + vacanciesTitlesSet.size() + " vacancies titles");

        return vacanciesTitlesSet;
    }

}