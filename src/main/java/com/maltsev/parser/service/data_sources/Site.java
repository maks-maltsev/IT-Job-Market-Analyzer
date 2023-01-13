package com.maltsev.parser.service.data_sources;

import java.io.IOException;
import java.util.Set;

public interface Site {
//    Set<String> selectVacanciesTitles(String siteLink) throws IOException;
    Set<String> selectVacanciesDescriptions(String siteLink) throws IOException, InterruptedException;
}
