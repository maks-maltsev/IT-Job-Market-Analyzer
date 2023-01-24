package com.maltsev.vacancies_parser.service.date_manager;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
    private static Date date = new Date();

    public static String getFormattedDate() {
        return formatter.format(date);
    }
}
