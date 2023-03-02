package com.maltsev.vacanciesparser.service.parser.impl;

import com.maltsev.vacanciesparser.service.datasources.impl.WorkUa;
import com.maltsev.vacanciesparser.service.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class WorkUaParser implements Parser {

    private final WorkUa workUa;

    @Autowired
    public WorkUaParser(WorkUa workUa) {
        this.workUa = workUa;
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() throws IOException, InterruptedException {
        Set<String> vacanciesDescriptionsSet = new HashSet<>();

        for(int i = 1; i <= workUa.getPages(); i++) {
            Thread.sleep(100);
            Document linkDoc = Jsoup.connect(workUa.getVacanciesPageUrl() + i).get();
            Elements detailsLink = linkDoc.getElementsByTag("h2");
            try {
                detailsLink.forEach(link -> {
                    Element vacancyLink = link.child(0);
                    try {
                        Document descriptionDoc = Jsoup
                                .connect(workUa.getUrl() + vacancyLink.attr("href"))
                                .get();
                        vacanciesDescriptionsSet
                                .add(descriptionDoc
                                        .getElementsByAttributeValue("id", "job-description")
                                        .text());
                    } catch (IOException e) {

                    }
                });
            } catch (IndexOutOfBoundsException e) {}

        }

        return vacanciesDescriptionsSet;
    }

    @Override
    public Set<String> getAllVacanciesTitles() throws IOException {
        Set<String> vacanciesTitlesSet = new HashSet<>();

        for(int i = 1; i <= workUa.getPages(); i++) {
            Document titlesDoc = Jsoup.connect(workUa.getVacanciesPageUrl() + i).get();
            Elements vacanciesTitles = titlesDoc.getElementsByTag("h2");
            try {
                vacanciesTitles.forEach(tit -> {
                    Element title = tit.child(0);
                    vacanciesTitlesSet.add(title.text());
                });
            } catch (IndexOutOfBoundsException e) {}
        }

        return vacanciesTitlesSet;
    }

}