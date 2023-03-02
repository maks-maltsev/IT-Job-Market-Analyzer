package com.maltsev.vacanciesparser.service.datasources.impl;

import com.maltsev.vacanciesparser.service.datasources.Site;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class JobsUa implements Site {

    private final int pages = 10;
    private final String url = "https://jobs.ua";
    private final String vacanciesPageUrl = "https://jobs.ua/vacancy/it_web_specialists/page-";

}