package com.maltsev.vacanciesparser.service.datahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * This class is responsible for scheduling the insertion of new vacancy data into the database.
 * It uses the VacancyDataInserter class to insert new data and runs on a schedule specified by a cron expression.
 */
@Service
@EnableScheduling
public class VacancyDataScheduler {

    private final VacancyDataInserter vacancyDataInserter;

    @Autowired
    public VacancyDataScheduler(VacancyDataInserter vacancyDataInserter) {
        this.vacancyDataInserter = vacancyDataInserter;
    }

    @Scheduled(cron = "0 1 0 1 * *")
    private void insertNewDataIntoDB() {
        vacancyDataInserter.insertNewDataIntoDB();
    }

}