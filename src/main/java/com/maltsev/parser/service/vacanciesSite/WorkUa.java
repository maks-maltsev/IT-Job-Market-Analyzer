package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
public class WorkUa extends AbstractSite {
    private Set<String> vacanciesTitlesSet = new HashSet<>();
    private Set<String> vacanciesDescriptionsSet = new HashSet<>();
    //143 pages
    private int pages = 100;
    private String siteName = "https://www.work.ua";
    private String siteLink = "https://www.work.ua/ru/jobs-it/?page=";

    @Override
    public Set<String> selectVacanciesTitles(String siteLink) throws IOException {
        System.out.println("Сбор заголовков с сайта " + siteName + " ...");
        for(int i = 1; i <= pages; i++) {
            Document titlesDoc = Jsoup.connect(siteLink + i).get();
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

    @Override
    public Set<String> selectDescriptions(String siteLink) throws IOException, InterruptedException {
        System.out.println("Збір описів ваканасій з сайта " + siteName + " ...");
        for(int i = 1; i <= pages; i++) {
            Thread.sleep(100);
            Document linkDoc = Jsoup.connect(siteLink + i).get();
            Elements detailsLink = linkDoc.getElementsByTag("h2");
            try {
                detailsLink.forEach(link -> {
                    Element vacancyLink = link.child(0);
                    try {
                        Document descriptionDoc = Jsoup.connect(siteName + vacancyLink.attr("href")).get();
                        vacanciesDescriptionsSet.add(descriptionDoc.getElementsByAttributeValue("id", "job-description").text());
                    } catch (IOException e) {}
                });
            } catch (IndexOutOfBoundsException e) {}
            System.out.println(siteName + i + " % loaded...");
        }
        return vacanciesDescriptionsSet;
    }

}