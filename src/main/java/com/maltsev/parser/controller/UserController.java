package com.maltsev.parser.controller;

import com.maltsev.parser.entity.Subscriber;
import com.maltsev.parser.repository.SubscriberRepository;
import com.maltsev.parser.service.emailSender.EmailSenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SubscriberRepository subscriberRepository;

    private final EmailSenderService senderService;

    public UserController(SubscriberRepository subscriberRepository, EmailSenderService senderService) {
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
        return "confirm-form";
    }

    @PostMapping("/confirmation")
    public String confirmationPost(@RequestParam(value = "confirmationEmail", required = true) String email,
                                   @RequestParam(value = "confirmationCode", required = true) int code){
        Subscriber subscriber = new Subscriber();
        subscriber = subscriberRepository.findFirstByEmail(email);
        if(subscriber.getCode() == code){
            subscriber.setConfirmation(true);
            subscriberRepository.save(subscriber);
            return "redirect:/";
        }
        else{
            return "confirm-form";
        }
    }

    @GetMapping("/unsubscribe")
    public String unsubscribeGet(){
        return "unsubscribe-form";
    }

    @PostMapping("/unsubscribe")
    public String unsubscribePost(@RequestParam(value = "confirmationEmail", required = true) String email){
        if(subscriberRepository.existsByEmail(email)) {
            Subscriber subscriber = new Subscriber();
            subscriber = subscriberRepository.findFirstByEmail(email);
            System.out.println(subscriber.toString());
            subscriberRepository.delete(subscriber);
            return "redirect:/";
        }
        return "redirect:/unsubscribe";
    }

}