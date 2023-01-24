package com.maltsev.vacancies_parser.controller;

import com.maltsev.vacancies_parser.entity.Subscriber;
import com.maltsev.vacancies_parser.repository.SubscriberRepository;
import com.maltsev.vacancies_parser.service.email_sander.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final SubscriberRepository subscriberRepository;
    private final EmailSenderService senderService;

    @Autowired
    public UserController(SubscriberRepository subscriberRepository,
                          EmailSenderService senderService) {

        this.subscriberRepository = subscriberRepository;
        this.senderService = senderService;

    }


    @GetMapping("/subscribe")
    public String subscribe(@RequestParam(value = "emailSub", required = true) String emailSub){
        senderService.confirmMessage(emailSub);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmation(){
        return "subscription-views/confirm-form";
    }

    @PostMapping("/confirmation")
    public String confirmationPost(@RequestParam(value = "confirmationEmail", required = true) String email,
                                   @RequestParam(value = "confirmationCode", required = true) int code) {

        Subscriber subscriber = subscriberRepository.findFirstByEmail(email);
        if(subscriber.getCode() == code){
            subscriber.setConfirmation(true);
            subscriberRepository.save(subscriber);
            return "redirect:/";
        }
        else{
            return "subscription-views/confirm-form";
        }

    }

    @GetMapping("/unsubscribe")
    public String unsubscribeGet(){
        return "subscription-views/unsubscribe-form";
    }

    @PostMapping("/unsubscribe")
    public String unsubscribePost(@RequestParam(value = "confirmationEmail", required = true) String email) {

        if(subscriberRepository.existsByEmail(email)) {
            Subscriber subscriber = subscriberRepository.findFirstByEmail(email);
            subscriberRepository.delete(subscriber);
            return "redirect:/";
        }
        return "redirect:/unsubscribe";

    }

}
