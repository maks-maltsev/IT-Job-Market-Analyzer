package com.maltsev.vacanciesparser.service.notification;

import com.maltsev.vacanciesparser.entity.Subscriber;
import com.maltsev.vacanciesparser.repository.SubscriberRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class provides functionality to send email notifications to subscribers
 * about updated vacancies, technologies, and requirements
 */
@Service
@EnableScheduling
public class EmailSenderService {

    private static final String EMAIL_MESSAGE = "Information about popular vacancies, technologies and requirements has been updated. " +
            "You can check it! https://itratings.com If you dont want to receive the update messages, you also can unsubscribe on https://itratings.com/unsubscribe ";
    private final JavaMailSender mailSender;
    private final SubscriberRepository subscriberRepository;
    private final ApplicationContext applicationContext;

    public EmailSenderService(JavaMailSender mailSender,
                              SubscriberRepository subscriberRepository, ApplicationContext applicationContext) {
        this.mailSender = mailSender;
        this.subscriberRepository = subscriberRepository;
        this.applicationContext = applicationContext;
    }

    @Scheduled(cron = "0 0 15 1 * *")
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        List<Subscriber> subscribers = subscriberRepository.findSubscribersByConfirmationIsTrue();
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
        message.setText(EMAIL_MESSAGE);
        message.setSubject("Vacancies ratings have been updated");
        mailSender.send(message);
    }

    public void confirmMessage(String toEmail) {
        if (!subscriberRepository.existsByEmail(toEmail)) {
            Random random = new Random();
            int code = random.nextInt(10000);
            SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);
            message.setFrom("yamaltsevm@gmail.com");
            message.setTo(toEmail);
            message.setText("Your activation code is: " + code + ", please enter this code on https://itratings.com/confirmation");
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
