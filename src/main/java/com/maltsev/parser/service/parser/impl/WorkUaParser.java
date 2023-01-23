package com.maltsev.parser.service.parser.impl;

import com.maltsev.parser.service.data_sources.impl.WorkUa;
import com.maltsev.parser.service.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class WorkUaParser implements Parser {
    private Set<String> vacanciesTitlesSet;
    private Set<String> vacanciesDescriptionsSet;
    private WorkUa workUa;

    public WorkUaParser() {
        this.vacanciesDescriptionsSet = new HashSet<>();
        this.vacanciesTitlesSet = new HashSet<>();
        this.workUa = new WorkUa();
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() throws IOException, InterruptedException {
        System.out.println("Собираю десрипы с воркуа");
        for(int i = 1; i <= workUa.getPages(); i++) {
            Thread.sleep(100);
            Document linkDoc = Jsoup.connect(workUa.getSiteLink() + i).get();
            Elements detailsLink = linkDoc.getElementsByTag("h2");
            try {
                detailsLink.forEach(link -> {
                    Element vacancyLink = link.child(0);
                    try {
                        Document descriptionDoc = Jsoup.connect(workUa.getSiteName() + vacancyLink.attr("href")).get();
                        vacanciesDescriptionsSet.add(descriptionDoc.getElementsByAttributeValue("id", "job-description").text());
                    } catch (IOException e) {}
                });
            } catch (IndexOutOfBoundsException e) {}

        }
        System.out.println("закончил собирать дескрипы с ворк уа");
        return vacanciesDescriptionsSet;

    }

    @Override
    public Set<String> getAllVacanciesTitles() throws IOException {
        System.out.println("Собираю титлы с воркуа");
        for(int i = 1; i <= workUa.getPages(); i++) {
            Document titlesDoc = Jsoup.connect(workUa.getSiteLink() + i).get();
            Elements vacanciesTitles = titlesDoc.getElementsByTag("h2");
            try {
                vacanciesTitles.forEach(tit -> {
                    Element title = tit.child(0);
                    vacanciesTitlesSet.add(title.text());
                });
            } catch (IndexOutOfBoundsException e) {}
        }
        System.out.println("закончил собирать титлы с ворк уа");
        return vacanciesTitlesSet;

    }
}