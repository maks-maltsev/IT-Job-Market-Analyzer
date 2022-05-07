package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WorkUa extends Site{
    @Getter
    private Set<String> vacanciesSet = new HashSet<>();
    @Getter
    private String siteLink = "https://www.work.ua/ru/jobs-it/?page=";

    @Override
    public Set<String> selectAllVacancies(String siteLink) throws IOException {
        System.out.println("Сбор информации с сайта work.ua ...");
        for(int i = 1; i <= 100; i++) {
            Document doc = Jsoup.connect(siteLink + i).get();
            Elements programmingLanguageName = doc.getElementsByTag("h2");

            try {
                programmingLanguageName.forEach(name -> {
                    Element element = name.child(0);
                    vacanciesSet.add(element.text());
                });
            } catch (IndexOutOfBoundsException e) {}
        }
        System.out.println("Данные собраны. Найдено вакансий: " + vacanciesSet.size());

        return vacanciesSet;
    }

}
