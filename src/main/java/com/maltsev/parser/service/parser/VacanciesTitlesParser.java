package com.maltsev.parser.service.parser;

import java.io.IOException;
import java.util.Set;

public interface VacanciesTitlesParser {
    Set<String> selectVacanciesTitles(String siteLink) throws IOException;
}
