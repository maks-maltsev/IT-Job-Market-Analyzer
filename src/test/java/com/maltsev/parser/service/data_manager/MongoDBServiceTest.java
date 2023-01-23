package com.maltsev.parser.service.data_manager;

import com.maltsev.parser.service.data_manager.MongoDBService;
import org.junit.Before;
import org.junit.Test;

public class MongoDBServiceTest {

    MongoDBService mongoDBService;

    @Before
    public void before() {
        mongoDBService = new MongoDBService();
    }


    @Test
    public void shouldStartThreeThreadsSimultaneously() throws Exception {
        mongoDBService.getAllVacanciesData();
    }
}
