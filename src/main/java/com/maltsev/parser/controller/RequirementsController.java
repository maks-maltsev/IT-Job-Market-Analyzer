package com.maltsev.parser.controller;

import com.maltsev.parser.repository.FrameworksRepos;
import com.maltsev.parser.repository.LanguageRepos;
import com.maltsev.parser.repository.RequirementsRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RequirementsController {
    @Autowired
    FrameworksRepos frameworksRepos;
    @Autowired
    LanguageRepos languageRepos;
    @Autowired
    RequirementsRepos requirementsRepos;
    private static int total;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/requirements")
    public String requirementsPage (Model model){
        ArrayList<String> requirementsName = requirementsRepos.selectRequirementsArrayWhereDateIs(formatter.format(date));
        ArrayList<Double> requirementsAmount = requirementsRepos.selectRequirementsAmountArrayWhereDateIs(formatter.format(date));

        FunctionsForAllControllers.makeIntArrayToPercentDouble(requirementsName, requirementsAmount, total);

        model.addAttribute("requirementsName", requirementsName);
        model.addAttribute("requirementsAmount", requirementsAmount);
        return "requirements-popularity";
    }

    @GetMapping("/requirementsDate")
    public String pickRequirementsStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                                            Model model){
        ArrayList<String> requirementsName = requirementsRepos.selectRequirementsArrayWhereDateIs(chosenDate);
        ArrayList<Double> requirementsAmount = requirementsRepos.selectRequirementsAmountArrayWhereDateIs(chosenDate);

        FunctionsForAllControllers.makeIntArrayToPercentDouble(requirementsName, requirementsAmount, total);

        model.addAttribute("requirementsName", requirementsName);
        model.addAttribute("requirementsAmount", requirementsAmount);
        return "requirements-popularity";
    }

}
