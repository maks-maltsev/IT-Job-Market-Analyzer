package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DjinniCo extends Site {

    @Getter
    private Set<String> vacanciesSet = new HashSet<>();
    @Getter
    private String siteLink = "https://djinni.co/developers/?from=dou-footer&page=";

    @Override
    public Set<String> selectAllVacancies(String siteLink) throws IOException{
        System.out.println("Сбор информации с сайта djinni.co ...");
        for(int i = 1; i <= 80; i++) {
            Document doc = Jsoup.connect(siteLink + i).get();
            Elements programmingLanguageName = doc.getElementsByClass("profile");

            try {
                programmingLanguageName.forEach(name -> {
                    vacanciesSet.add(name.text());
                });
            }catch (IndexOutOfBoundsException e){}
        }
        System.out.println("Данные собраны. Найдено вакансий: " + vacanciesSet.size());
        return vacanciesSet;
    }
}
