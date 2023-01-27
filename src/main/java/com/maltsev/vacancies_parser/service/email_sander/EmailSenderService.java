package com.maltsev.vacancies_parser.service.email_sander;

import com.maltsev.vacancies_parser.entity.Subscriber;
import com.maltsev.vacancies_parser.repository.SubscriberRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@EnableScheduling
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final SubscriberRepository subscriberRepository;
    private final ApplicationContext applicationContext;

    public EmailSenderService(JavaMailSender mailSender,
                              SubscriberRepository subscriberRepository, ApplicationContext applicationContext) {
        this.mailSender = mailSender;
        this.subscriberRepository = subscriberRepository;
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 10 1 * *")
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){

        List<Subscriber> subscribers = subscriberRepository
                .findSubscribersByConfirmationIsTrue();
        List<String> subscribersEmails = new ArrayList<>();

        for (Subscriber subscriber : subscribers){
            subscribersEmails.add(subscriber.getEmail());
        }

        for (String subscriberEmail : subscribersEmails) {
            sendEmail(subscriberEmail);
        }

    }

    public void sendEmail(String toEmail){

        SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);
        message.setFrom("yamaltsevm@gmail.com");
        message.setTo(toEmail);
        message.setText("Information about popular vacancies, technologies and requirements has been updated. You can check it! https://itratings.com " +
                "If you dont want to receive the update messages, you also can unsubscribe on https://itratings.com/unsubscribe ");
        message.setSubject("The IT ratings was updated");
        mailSender.send(message);

    }

    public void confirmMessage(String toEmail) {

        if (!subscriberRepository.existsByEmail(toEmail)) {
            Random random = new Random();
            int code = random.nextInt(10000);
            SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);
            message.setFrom("yamaltsevm@gmail.com");
            message.setTo(toEmail);
            message.setText("Your activation code is: " + code + ", please enter this code on https://localhost:8080/confirmation");
            message.setSubject("Confirmation");

            Subscriber subscriber = applicationContext.getBean(Subscriber.class);
            subscriber.setEmail(toEmail);
            subscriber.setConfirmation(false);
            subscriber.setCode(code);

            subscriberRepository.save(subscriber);

            mailSender.send(message);
        }

    }

}
