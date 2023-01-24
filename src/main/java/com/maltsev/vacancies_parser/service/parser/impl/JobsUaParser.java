package com.maltsev.vacancies_parser.service.parser.impl;

import com.maltsev.vacancies_parser.service.data_sources.impl.JobsUa;
import com.maltsev.vacancies_parser.service.parser.Parser;
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

    private final JobsUa jobsUa;

    @Autowired
    public JobsUaParser(JobsUa jobsUa) {
        this.jobsUa = jobsUa;
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() throws IOException {

        Set<String> vacanciesDescriptionsSet = new HashSet<>();
        for(int i = 1; i <= jobsUa.getPages(); i++) {
            Document linksDoc = Jsoup.connect(jobsUa.getSiteLink() + i).get();
            Elements links = linksDoc
                    .getElementsByClass("b-vacancy__top__title js-item_title");
            links.forEach(link ->{
                try {
                    Document vacancyDetails = Jsoup
                            .connect(link.attr("href"))
                            .get();
                    vacanciesDescriptionsSet
                            .add(vacancyDetails
                                    .getElementsByClass("b-vacancy-full__block b-text js-phone-replace")
                                    .text());

                } catch (IOException e) {}
            });
        }
        return vacanciesDescriptionsSet;

    }

    @Override
    public Set<String> getAllVacanciesTitles() throws IOException {

        Set<String> vacanciesTitlesSet = new HashSet<>();
        for (int i = 1; i <= jobsUa.getPages(); i++) {
            Document doc = Jsoup.connect(jobsUa.getSiteLink() + i).get();
            Elements vacanciesTitles = doc
                    .getElementsByClass("b-vacancy__top__title js-item_title");
            try {
                vacanciesTitles.forEach(title -> {
                    vacanciesTitlesSet.add(title.text());
                });
            } catch (IndexOutOfBoundsException e) {
            }
        }
        return vacanciesTitlesSet;

    }

}
