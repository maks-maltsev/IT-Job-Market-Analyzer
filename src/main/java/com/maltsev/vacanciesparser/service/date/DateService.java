package com.maltsev.vacanciesparser.service.date;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A service class that provides date-related functionality for the vacancies parser application.
 */
@Service
public class DateService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    /**
     * @return a string representing the current date in "yyyy-MM" format.
     */
    public static String getFormattedDate() {
        return LocalDate.now().format(FORMATTER);
    }

}