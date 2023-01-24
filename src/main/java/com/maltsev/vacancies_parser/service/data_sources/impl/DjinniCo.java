package com.maltsev.vacancies_parser.service.data_sources.impl;

import com.maltsev.vacancies_parser.service.data_sources.Site;
import lombok.Getter;

import org.springframework.stereotype.Service;

@Getter
@Service
public class DjinniCo implements Site {

    private final int pages = 100;
    private final String siteName = "https://djinni.co";
    private final String siteLink = "https://djinni.co/developers/?from=dou-footer&page=";

}
