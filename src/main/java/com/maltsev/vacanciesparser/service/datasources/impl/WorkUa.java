package com.maltsev.vacanciesparser.service.datasources.impl;

import com.maltsev.vacanciesparser.service.datasources.Site;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class WorkUa implements Site {

    private final int pages = 10;
    private final String url = "https://www.work.ua";
    private final String vacanciesPageUrl = "https://www.work.ua/ru/jobs-it/?page=";

}