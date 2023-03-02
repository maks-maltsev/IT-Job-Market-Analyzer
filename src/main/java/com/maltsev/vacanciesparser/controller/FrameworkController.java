package com.maltsev.vacanciesparser.controller;

import com.maltsev.vacanciesparser.entity.Framework;
import com.maltsev.vacanciesparser.repository.FrameworkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static com.maltsev.vacanciesparser.service.date.DateService.getFormattedDate;

@Controller
@RequestMapping("/frameworks")
public class FrameworkController {

    private final FrameworkRepository frameworkRepository;
    private final Map<String, Integer> frameworksMap;

    @Autowired
    public FrameworkController(FrameworkRepository frameworkRepository) {
        this.frameworkRepository = frameworkRepository;
        this.frameworksMap = new LinkedHashMap<>();
    }

    @GetMapping
    public String getFrameworksRating(@RequestParam(value = "date", required = false) Optional<String> date,
                                      Model model) {
        List<Framework> frameworkList = date.map(frameworkRepository::findFrameworksByDateOrderByAmountDesc)
                .orElseGet(() -> frameworkRepository.findFrameworksByDateOrderByAmountDesc(getFormattedDate()));
        if(frameworkList.isEmpty()) {
            frameworkList = frameworkRepository.findFrameworksByDateOrderByAmountDesc(getFormattedDate());
        }
        frameworksMap.clear();
        buildFrameworksMap(frameworkList);

        model.addAttribute("frameworks", frameworksMap.keySet());
        model.addAttribute("amount", frameworksMap.values());

        return "ratings-views/frameworks-popularity";
    }

    private void buildFrameworksMap(List<Framework> frameworkList) {
        for (Framework framework : frameworkList) {
            frameworksMap.put(framework.getName(), framework.getAmount());
        }
    }

}
