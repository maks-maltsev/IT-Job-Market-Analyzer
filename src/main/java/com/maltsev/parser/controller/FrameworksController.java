package com.maltsev.parser.controller;

import com.maltsev.parser.entity.Framework;
import com.maltsev.parser.repository.FrameworkRepository;
import com.maltsev.parser.service.intToDoubleConverter.MakeIntArrayToPercentDoubleArray;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/frameworks")
public class FrameworksController {

    private final FrameworkRepository frameworkRepository;
    private int total;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    public FrameworksController(FrameworkRepository frameworkRepository) {
        this.frameworkRepository = frameworkRepository;
    }

    @GetMapping
    public String frameworksPage(Model model){
        List<Framework> frameworkList = frameworkRepository.findFrameworksByDateOrderByAmountDesc(formatter.format(date));
        List<String> frameworksNames = new ArrayList<>();
        List<Double> frameworksAmount = new ArrayList<>();

        for (int i = 0; i < frameworkList.size(); i++){
            frameworksNames.add(frameworkList.get(i).getName());
            frameworksAmount.add((double) frameworkList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(frameworksNames, frameworksAmount, total);

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", frameworksAmount);
        return "frameworks-popularity";
    }

    @GetMapping("/frameworksDate")
    public String pickFrameworksStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                    Model model){
        List<Framework> frameworkList = frameworkRepository.findFrameworksByDateOrderByAmountDesc(chosenDate);
        List<String> frameworksNames = new ArrayList<>();
        List<Double> frameworksAmount = new ArrayList<>();

        for (int i = 0; i < frameworkList.size(); i++){
            frameworksNames.add(frameworkList.get(i).getName());
            frameworksAmount.add((double) frameworkList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(frameworksNames, frameworksAmount, total);

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", frameworksAmount);
        return "frameworks-popularity";
    }

}
