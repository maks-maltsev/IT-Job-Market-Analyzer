package com.maltsev.parser.service.data_sources.impl;

import com.maltsev.parser.service.data_sources.Site;
import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
public class WorkUa implements Site {

    //143 pages
    private final int pages = 100;
    private final String siteName = "https://www.work.ua";
    private final String siteLink = "https://www.work.ua/ru/jobs-it/?page=";

}