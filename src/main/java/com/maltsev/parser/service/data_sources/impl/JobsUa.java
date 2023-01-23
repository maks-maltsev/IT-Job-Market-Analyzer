package com.maltsev.parser.service.data_sources.impl;

import com.maltsev.parser.service.data_sources.Site;
import com.maltsev.parser.service.parser.Parser;
import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

@Getter
public class JobsUa implements Site {

    //13 pages
    private final int pages = 10;
    private final String siteLink = "https://jobs.ua/vacancy/it_web_specialists/page-";
    private final String siteName = "https://jobs.ua";

}

