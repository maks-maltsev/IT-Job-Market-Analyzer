package com.maltsev.vacancies_parser.service.data_manager;

import com.maltsev.vacancies_parser.controller.FrameworkController;
import com.maltsev.vacancies_parser.entity.Framework;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBServiceTest {

    @Before
    public void setUp() {
        Framework framework = new Framework("Laravel", 5, "20-2020");
    }

    @Test
    public void test() throws Exception {

    }




}
