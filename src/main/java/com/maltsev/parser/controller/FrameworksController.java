package com.maltsev.parser.controller;

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

@Controller
public class FrameworksController {
    @Autowired
    FrameworksRepos frameworksRepos;
    private static int total;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/frameworks")
    public String frameworksPage(Model model){
        ArrayList<String> frameworksNames = frameworksRepos.selectFrameworksArrayWhereDateIs(formatter.format(date));
        ArrayList<Double> frameworksAmount = frameworksRepos.selectFrameworksAmountArrayWhereDateIs(formatter.format(date));

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(frameworksNames, frameworksAmount, total);

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", frameworksAmount);
        return "frameworks-popularity";
    }

    @GetMapping("/frameworksDate")
    public String pickFrameworksStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                    Model model){
        ArrayList<String> frameworksNames = frameworksRepos.selectFrameworksArrayWhereDateIs(chosenDate);
        ArrayList<Double> frameworksAmount = frameworksRepos.selectFrameworksAmountArrayWhereDateIs(chosenDate);

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(frameworksNames, frameworksAmount, total);

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", frameworksAmount);
        return "frameworks-popularity";
    }

}
