package com.maltsev.parser;

import com.maltsev.parser.service.addStatsToDB.StatsAdditionToDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ParserApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}
/*
	@Scheduled(cron = "0 1 0 1 * *")
	@Bean(initMethod="runAfterObjectCreated")
	public StatsAdditionToDB getFunnyBean() {
		return new StatsAdditionToDB();
	}
*/
}



