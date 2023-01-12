package com.maltsev.parser;

import com.maltsev.parser.repository.SubscriberRepository;
import com.maltsev.parser.service.email_sander.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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



