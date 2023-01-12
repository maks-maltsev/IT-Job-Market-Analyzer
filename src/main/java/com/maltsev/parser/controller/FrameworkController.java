package com.maltsev.parser.controller;

import com.maltsev.parser.entity.Framework;
import com.maltsev.parser.repository.FrameworkRepository;
import com.maltsev.parser.service.date_manager.DateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static com.maltsev.parser.service.date_manager.DateService.*;

@Controller
public class FrameworkController {

    private final FrameworkRepository frameworkRepository;
    private Map<String, Double> frameworksMap = new LinkedHashMap<>();

    public FrameworkController(FrameworkRepository frameworkRepository) {
        this.frameworkRepository = frameworkRepository;
    }

    @GetMapping("/frameworks")
    public String showFrameworksRating(Model model) {

        List<Framework> frameworkList = frameworkRepository
                .findFrameworksByDateOrderByAmountDesc(DateService.formatter.format(date));

        for (Framework framework : frameworkList){
            frameworksMap.put(framework.getName(), (double) framework.getAmount());
        }

        model.addAttribute("frameworks", frameworksMap.keySet());
        model.addAttribute("frameworksAmount", frameworksMap.values());

        return "ratings-views/frameworks-popularity";
    }

    @GetMapping("/frameworks-date")
    public String showFrameworksRatingByDate(
            @RequestParam(value = "chosenDate", defaultValue = "2022-05") String chosenDate,
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
