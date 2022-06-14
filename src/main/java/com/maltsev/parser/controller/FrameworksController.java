package com.maltsev.parser.controller;

import com.maltsev.parser.model.Frameworks;
import com.maltsev.parser.model.Requirements;
import com.maltsev.parser.repository.FrameworksRepos;
import com.maltsev.parser.service.intToDoubleConverter.MakeIntArrayToPercentDoubleArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FrameworksController {
    @Autowired
    FrameworksRepos frameworksRepos;
    private static int total;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/frameworks")
    public String frameworksPage(Model model){
        List<Frameworks> frameworksList = frameworksRepos.findFrameworksByDateOrderByAmountDesc(formatter.format(date));
        List<String> frameworksNames = new ArrayList<>();
        List<Double> frameworksAmount = new ArrayList<>();

        for (int i = 0; i < frameworksList.size(); i++){
            frameworksNames.add(frameworksList.get(i).getName());
            frameworksAmount.add((double) frameworksList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(frameworksNames, frameworksAmount, total);

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", frameworksAmount);
        return "frameworks-popularity";
    }

    @GetMapping("/frameworksDate")
    public String pickFrameworksStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                    Model model){
        List<Frameworks> frameworksList = frameworksRepos.findFrameworksByDateOrderByAmountDesc(chosenDate);
        List<String> frameworksNames = new ArrayList<>();
        List<Double> frameworksAmount = new ArrayList<>();

        for (int i = 0; i < frameworksList.size(); i++){
            frameworksNames.add(frameworksList.get(i).getName());
            frameworksAmount.add((double) frameworksList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(frameworksNames, frameworksAmount, total);

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", frameworksAmount);
        return "frameworks-popularity";
    }

}
