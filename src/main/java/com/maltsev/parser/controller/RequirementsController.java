package com.maltsev.parser.controller;

import com.maltsev.parser.model.Requirements;
import com.maltsev.parser.model.Vacancies;
import com.maltsev.parser.repository.FrameworksRepos;
import com.maltsev.parser.repository.VacanciesRepos;
import com.maltsev.parser.repository.RequirementsRepos;
import com.maltsev.parser.service.intToDoubleConverter.MakeIntArrayToPercentDoubleArray;
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
    RequirementsRepos requirementsRepos;
    private static int total;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/requirements")
    public String requirementsPage (Model model){
        List<Requirements> requirementsList = requirementsRepos.findRequirementsByDateOrderByAmountDesc(formatter.format(date));
        List<String> requirementsName = new ArrayList<>();
        List<Double> requirementsAmount = new ArrayList<>();

        for (int i = 0; i < requirementsList.size(); i++){
            requirementsName.add(requirementsList.get(i).getName());
            requirementsAmount.add((double) requirementsList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(requirementsName, requirementsAmount, total);

        model.addAttribute("requirementsName", requirementsName);
        model.addAttribute("requirementsAmount", requirementsAmount);
        return "requirements-popularity";
    }

    @GetMapping("/requirementsDate")
    public String pickRequirementsStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                                            Model model){
        List<Requirements> requirementsList = requirementsRepos.findRequirementsByDateOrderByAmountDesc(chosenDate);
        List<String> requirementsName = new ArrayList<>();
        List<Double> requirementsAmount = new ArrayList<>();

        for (int i = 0; i < requirementsList.size(); i++){
            requirementsName.add(requirementsList.get(i).getName());
            requirementsAmount.add((double) requirementsList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(requirementsName, requirementsAmount, total);

        model.addAttribute("requirementsName", requirementsName);
        model.addAttribute("requirementsAmount", requirementsAmount);
        return "requirements-popularity";
    }

}
