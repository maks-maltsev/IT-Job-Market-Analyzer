package com.maltsev.parser.controller;

import com.maltsev.parser.repository.FrameworksRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
