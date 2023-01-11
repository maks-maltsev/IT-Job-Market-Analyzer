package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
public class DjinniCo extends AbstractSite{

    private Set<String> vacanciesTitlesSet = new HashSet<>();
    private Set<String> vacanciesDescriptionsSet = new HashSet<>();
    //4500 pages
    private int pages = 100;
    private String siteName = "https://djinni.co";
    private String siteLink = "https://djinni.co/developers/?from=dou-footer&page=";

    @Override
    public Set<String> selectVacanciesTitles(String siteLink) throws IOException{
        System.out.println("Сбор заголовков с сайта " + siteName + " ...");
        for(int i = 1; i <= pages; i++) {
            Document titlesDoc = Jsoup.connect(siteLink + i).get();
            Elements vacanciesTitles = titlesDoc.getElementsByClass("profile");
            try {
                vacanciesTitles.forEach(title -> {
                    vacanciesTitlesSet.add(title.text());
                });
            }catch (IndexOutOfBoundsException e){}
        }
        return vacanciesTitlesSet;
    }

    @Override
    public Set<String> selectDescriptions(String siteLink) throws IOException {
        System.out.println("Збір описів ваканасій з сайта " + siteName + " ...");
        for(int i = 1; i <= pages; i++){
            Document linkDoc = Jsoup.connect(siteLink + i).get();
            Elements detailsLink = linkDoc.getElementsByClass("profile");
            try{
                detailsLink.forEach(link -> {
                    try {
                        Document vacancyDetails = Jsoup.connect(siteName + link.attr("href")).get();
                        vacanciesDescriptionsSet.add(vacancyDetails.getElementsByClass("col-sm-8").text());
                    } catch (IOException e) { }
                });
                System.out.println(siteName + i + " % loaded...");
            }catch (IndexOutOfBoundsException e){}
        }
        return vacanciesDescriptionsSet;
    }

}
