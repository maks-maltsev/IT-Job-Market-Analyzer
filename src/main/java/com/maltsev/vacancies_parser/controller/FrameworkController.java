package com.maltsev.vacancies_parser.controller;

import com.maltsev.vacancies_parser.entity.Framework;
import com.maltsev.vacancies_parser.repository.FrameworkRepository;
import com.maltsev.vacancies_parser.service.date_manager.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class FrameworkController {

    private final FrameworkRepository frameworkRepository;
    private Map<String, Double> frameworksMap = new LinkedHashMap<>();

    @Autowired
    public FrameworkController(FrameworkRepository frameworkRepository) {
        this.frameworkRepository = frameworkRepository;
    }

    @GetMapping("/frameworks")
    public String showFrameworksRating(Model model) {

        List<Framework> frameworkList = frameworkRepository
                .findFrameworksByDateOrderByAmountDesc(DateService.getFormattedDate());

        for (Framework framework : frameworkList){
            frameworksMap.put(framework.getName(), (double) framework.getAmount());
        }

        model.addAttribute("frameworks", frameworksMap.keySet());
        model.addAttribute("frameworksAmount", frameworksMap.values());

        return "ratings-views/frameworks-popularity";
    }

    @GetMapping("/frameworks-date")
    public String showFrameworksRatingByDate(
            @RequestParam(value = "chosenDate") String chosenDate,
                    Model model){

        List<Framework> frameworkList = frameworkRepository
                .findFrameworksByDateOrderByAmountDesc(chosenDate);

        if(frameworkList.isEmpty()) {
            return "redirect:/frameworks";
        }

        for (Framework framework : frameworkList){
            frameworksMap.put(framework.getName(), (double) framework.getAmount());
        }

        model.addAttribute("frameworks", frameworksMap.keySet());
        model.addAttribute("frameworksAmount", frameworksMap.values());

        return "ratings-views/frameworks-popularity";
    }

}
