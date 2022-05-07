package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class JobsUa extends Site {

    @Getter
    private Set<String> vacanciesSet = new HashSet<>();
    @Getter
    private String siteLink = "https://jobs.ua/vacancy/it_web_specialists/page-";

    @Override
    public Set<String> selectAllVacancies(String siteLink) throws IOException {
        System.out.println("Сбор данных с сайта jobs.ua ...");
        for (int i = 1; i <= 10; i++) {
            Document doc = Jsoup.connect(siteLink + i).get();
            Elements programmingLanguageName = doc.getElementsByClass("b-vacancy__top__title js-item_title");

            try {
                programmingLanguageName.forEach(name -> {
                    vacanciesSet.add(name.text());
                });
            } catch (IndexOutOfBoundsException e) {
            }
        }
        System.out.println("Данные собраны. Найдено вакансий: " + vacanciesSet.size());
        return vacanciesSet;
    }
}

