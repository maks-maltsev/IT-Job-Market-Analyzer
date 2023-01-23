package com.maltsev.parser.service.parser.impl;

import com.maltsev.parser.service.data_sources.impl.JobsUa;
import com.maltsev.parser.service.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class JobsUaParser implements Parser {

    private Set<String> vacanciesTitlesSet;
    private Set<String> vacanciesDescriptionsSet;
    private JobsUa jobsUa;

    public JobsUaParser() {
        this.vacanciesDescriptionsSet = new HashSet<>();
        this.vacanciesTitlesSet = new HashSet<>();
        this.jobsUa = new JobsUa();
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() throws IOException {
        System.out.println("Собираю дескрипы с джобс уа");
        for(int i = 1; i <= jobsUa.getPages(); i++) {
            Document linksDoc = Jsoup.connect(jobsUa.getSiteLink() + i).get();
            Elements links = linksDoc.getElementsByClass("b-vacancy__top__title js-item_title");
            links.forEach(link ->{
                try {
                    Document vacancyDetails = Jsoup.connect(link.attr("href")).get();
                    vacanciesDescriptionsSet.add(vacancyDetails
                            .getElementsByClass("b-vacancy-full__block b-text js-phone-replace").text());

                } catch (IOException e) {}
            });
        }
        System.out.println("закончил собирать дескрипы с джобс уа");
        return vacanciesDescriptionsSet;

    }

    @Override
    public Set<String> getAllVacanciesTitles() throws IOException {
        System.out.println("Собираю титлы с джопс уа");
        for (int i = 1; i <= jobsUa.getPages(); i++) {
            Document doc = Jsoup.connect(jobsUa.getSiteLink() + i).get();
            Elements vacanciesTitles = doc.getElementsByClass("b-vacancy__top__title js-item_title");
            try {
                vacanciesTitles.forEach(title -> {
                    vacanciesTitlesSet.add(title.text());
                });
            } catch (IndexOutOfBoundsException e) {
            }
        }
        System.out.println("закончил собирать титлы с джобс уа");
        return vacanciesTitlesSet;

    }

}
