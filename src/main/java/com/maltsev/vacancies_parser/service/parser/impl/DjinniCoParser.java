package com.maltsev.vacancies_parser.service.parser.impl;

import com.maltsev.vacancies_parser.service.data_sources.impl.DjinniCo;
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
public class DjinniCoParser implements Parser {
    private final DjinniCo djinniCo;

    @Autowired
    public DjinniCoParser(DjinniCo djinniCo) {
        this.djinniCo = djinniCo;
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() throws IOException {

        Set<String> vacanciesDescriptionsSet = new HashSet<>();
        for(int i = 1; i <= djinniCo.getPages(); i++){
            Document linkDoc = Jsoup.connect(djinniCo.getSiteLink() + i).get();
            Elements detailsLink = linkDoc.getElementsByClass("profile");
            try{
                detailsLink.forEach(link -> {
                    try {
                        Document vacancyDetails = Jsoup
                                .connect(djinniCo.getSiteName() + link.attr("href"))
                                .get();
                        vacanciesDescriptionsSet
                                .add(vacancyDetails.getElementsByClass("col-sm-8")
                                .text());
                    } catch (IOException e) { }
                });
            }catch (IndexOutOfBoundsException e){}
        }
        return vacanciesDescriptionsSet;

    }

    @Override
    public Set<String> getAllVacanciesTitles() throws IOException {

        Set<String> vacanciesTitlesSet = new HashSet<>();
        for(int i = 1; i <= djinniCo.getPages(); i++) {
            Document titlesDoc = Jsoup.connect(djinniCo.getSiteLink() + i).get();
            Elements vacanciesTitles = titlesDoc.getElementsByClass("profile");
            try {
                vacanciesTitles.forEach(title -> {
                    vacanciesTitlesSet.add(title.text());
                });
            }catch (IndexOutOfBoundsException e){}
        }
        return vacanciesTitlesSet;

    }

}
