package com.maltsev.parser.service.emailSender;

import com.maltsev.parser.entity.Subscriber;
import com.maltsev.parser.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@EnableScheduling
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SubscriberRepository subscriberRepository;

    public void sendEmail(String toEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yamaltsevm@gmail.com");
        message.setTo(toEmail);
        message.setText("Information about popular vacancies, technologies and requirements just updated. You can check it! https://itratings.com " +
                "If you dont want to receive the update messages, you also can unsubscribe on https://localhost:8080/unsubscribe ");
        message.setSubject("The IT ratings is updated");

        mailSender.send(message);
    }

    public void confirmMessage(String toEmail){
        if(!subscriberRepository.existsByEmail(toEmail)) {
            Random random = new Random();
            int code = random.nextInt(10000);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("yamaltsevm@gmail.com");
            message.setTo(toEmail);
            message.setText("Your activation code is: " + code + ", please enter this code on https://localhost:8080/confirmation");
            message.setSubject("Confirmation");

            Subscriber subscriber = new Subscriber(toEmail, false, code);

            subscriberRepository.save(subscriber);

            mailSender.send(message);
        }
        else{
            System.out.println("Вже підписан");
        }
    }
}
