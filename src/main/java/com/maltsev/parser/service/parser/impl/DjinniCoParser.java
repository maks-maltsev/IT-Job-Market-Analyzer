package com.maltsev.parser.service.parser.impl;

import com.maltsev.parser.service.data_sources.impl.DjinniCo;
import com.maltsev.parser.service.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class DjinniCoParser implements Parser {
    private Set<String> vacanciesTitlesSet;
    private Set<String> vacanciesDescriptionsSet;

    private DjinniCo djinniCo;

    public DjinniCoParser() {
        this.vacanciesDescriptionsSet = new HashSet<>();
        this.vacanciesTitlesSet = new HashSet<>();
        this.djinniCo = new DjinniCo();
    }

    @Override
    public Set<String> getAllVacanciesDescriptions() throws IOException {
        System.out.println("Собираю дескрипшоны с джинни");
        for(int i = 1; i <= djinniCo.getPages(); i++){
            Document linkDoc = Jsoup.connect(djinniCo.getSiteLink() + i).get();
            Elements detailsLink = linkDoc.getElementsByClass("profile");
            try{
                detailsLink.forEach(link -> {
                    try {
                        Document vacancyDetails = Jsoup.connect(djinniCo.getSiteName() + link.attr("href")).get();
                        vacanciesDescriptionsSet.add(vacancyDetails.getElementsByClass("col-sm-8").text());
                    } catch (IOException e) { }
                });
            }catch (IndexOutOfBoundsException e){}
        }
        System.out.println("закончил собирать дескрипшоны с джинни");
        return vacanciesDescriptionsSet;

    }

    @Override
    public Set<String> getAllVacanciesTitles() throws IOException {
        System.out.println("Собираю титлы с джинни");
        for(int i = 1; i <= djinniCo.getPages(); i++) {
            Document titlesDoc = Jsoup.connect(djinniCo.getSiteLink() + i).get();
            Elements vacanciesTitles = titlesDoc.getElementsByClass("profile");
            try {
                vacanciesTitles.forEach(title -> {
                    vacanciesTitlesSet.add(title.text());
                });
            }catch (IndexOutOfBoundsException e){}
        }

        System.out.println("закончил собирать титлы с джинни");
        return vacanciesTitlesSet;

    }

}
