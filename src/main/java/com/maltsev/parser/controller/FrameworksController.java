package com.maltsev.parser.controller;

import com.maltsev.parser.repository.FrameworksRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class FrameworksController {
    @Autowired
    FrameworksRepos frameworksRepos;

    @GetMapping("/frameworks")
    public String frameworksPage(Model model){
        ArrayList<String> frameworksNames = frameworksRepos.selectFrameworksArray();
        ArrayList<Integer> amount = frameworksRepos.selectFrameworksAmountArray();

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", amount);
        return "frameworks-popularity";
    }

    @GetMapping("/frameworks/{date}")
    public String pickFrameworksStatsByDate(@RequestParam(name = "date") String date,
                    Model model){
        ArrayList<String> frameworksNames = frameworksRepos.selectFrameworksArrayWhereDateIs(date);
        ArrayList<Integer> frameworksAmount = frameworksRepos.selectFrameworksAmountArrayWhereDateIs(date);

        model.addAttribute("frameworks", frameworksNames);
        model.addAttribute("frameworksAmount", frameworksAmount);
        return "frameworks-popularity";
    }
}
