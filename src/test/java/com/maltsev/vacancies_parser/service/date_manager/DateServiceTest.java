package com.maltsev.vacancies_parser.service.date_manager;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DateServiceTest {

    @Test
    public void dateShouldBeEqualsToCurrentDate() {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String currentDate = formatter.format(date);

        assertEquals(currentDate, DateService.getFormattedDate());
    }
}
