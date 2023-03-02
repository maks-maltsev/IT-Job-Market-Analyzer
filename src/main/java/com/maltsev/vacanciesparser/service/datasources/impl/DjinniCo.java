package com.maltsev.vacanciesparser.service.datasources.impl;

import com.maltsev.vacanciesparser.service.datasources.Site;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class DjinniCo implements Site {

    private final int pages = 100;
    private final String url = "https://djinni.co";
    private final String vacanciesPageUrl = "https://djinni.co/jobs/?page=";

}