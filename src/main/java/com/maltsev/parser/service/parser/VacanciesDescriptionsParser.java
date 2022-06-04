package com.maltsev.parser.service.parser;

import java.io.IOException;
import java.util.Set;

public interface VacanciesDescriptionsParser {
    Set<String> selectDescriptions(String siteLink) throws IOException, InterruptedException;
}