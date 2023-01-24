package com.maltsev.vacancies_parser.service.data_sources.impl;

import com.maltsev.vacancies_parser.service.data_sources.Site;
import lombok.Getter;

import org.springframework.stereotype.Component;

@Getter
@Component
public class JobsUa implements Site {

    private final int pages = 10;
    private final String siteLink = "https://jobs.ua/vacancy/it_web_specialists/page-";
    private final String siteName = "https://jobs.ua";

}

