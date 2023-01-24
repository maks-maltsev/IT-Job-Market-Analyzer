package com.maltsev.vacancies_parser.service.data_sources.impl;

import com.maltsev.vacancies_parser.service.data_sources.Site;
import lombok.Getter;

import org.springframework.stereotype.Component;

@Getter
@Component
public class WorkUa implements Site {

    private final int pages = 100;
    private final String siteName = "https://www.work.ua";
    private final String siteLink = "https://www.work.ua/ru/jobs-it/?page=";

}