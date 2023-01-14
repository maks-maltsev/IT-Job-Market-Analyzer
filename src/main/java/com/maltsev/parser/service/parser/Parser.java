package com.maltsev.parser.service.parser;

import java.util.Set;

public interface Parser {
    public Set<String> getAllVacanciesDescriptions(String siteName);
    public Set<String> getAllVacanciesTitles();
}
