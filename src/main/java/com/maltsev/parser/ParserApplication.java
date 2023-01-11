package com.maltsev.parser;

import com.maltsev.parser.entity.Subscriber;
import com.maltsev.parser.repository.SubscriberRepository;
import com.maltsev.parser.service.addNewDataToDB.FillAllDBFields;
import com.maltsev.parser.service.emailSender.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ParserApplication {
	@Autowired
	private EmailSenderService senderService;
	@Autowired
	private SubscriberRepository subscriberRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}

//	@Scheduled(cron = "0 1 0 1 * *")
//	@Bean(initMethod="runAfterObjectCreated")
//	public FillAllDBFields getFunnyBean() {
//		return new FillAllDBFields();
//	}
//
//	@Scheduled(cron = "0 0 10 1 * *")
//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail(){
//		List<Subscriber> subscribers = subscriberRepository.findSubscribersByConfirmationIsTrue();
//		System.out.println(subscribers);
//		List<String> subscribersEmails = new ArrayList<>();
//		for (Subscriber subscriber : subscribers){
//			subscribersEmails.add(subscriber.getEmail());
//		}
//		System.out.println(subscribersEmails);
//		for (String subscriberEmail : subscribersEmails) {
//			senderService.sendEmail(subscriberEmail);
//		}
//
//	}
}



