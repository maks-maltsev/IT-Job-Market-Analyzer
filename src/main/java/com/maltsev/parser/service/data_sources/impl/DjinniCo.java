package com.maltsev.parser.service.data_sources.impl;

import com.maltsev.parser.service.data_sources.Site;
import lombok.Getter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
public class DjinniCo implements Site {

    //4500 pages
    private final int pages = 100;
    private final String siteName = "https://djinni.co";
    private final String siteLink = "https://djinni.co/developers/?from=dou-footer&page=";

}
