package com.maltsev.parser.controller;

import com.maltsev.parser.entity.Requirement;
import com.maltsev.parser.repository.RequirementRepository;
import com.maltsev.parser.service.intToDoubleConverter.MakeIntArrayToPercentDoubleArray;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/requirements")
public class RequirementsController {

    private final RequirementRepository requirementRepository;
    private int total;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    public RequirementsController(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @GetMapping
    public String requirementsPage (Model model){
        List<Requirement> requirementList = requirementRepository.findRequirementsByDateOrderByAmountDesc(formatter.format(date));
        List<String> requirementsName = new ArrayList<>();
        List<Double> requirementsAmount = new ArrayList<>();

        for (int i = 0; i < requirementList.size(); i++){
            requirementsName.add(requirementList.get(i).getName());
            requirementsAmount.add((double) requirementList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(requirementsName, requirementsAmount, total);

        model.addAttribute("requirementsName", requirementsName);
        model.addAttribute("requirementsAmount", requirementsAmount);
        return "requirements-popularity";
    }

    @GetMapping("/date")
    public String pickRequirementsStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                                            Model model){
        List<Requirement> requirementList = requirementRepository.findRequirementsByDateOrderByAmountDesc(chosenDate);
        List<String> requirementsName = new ArrayList<>();
        List<Double> requirementsAmount = new ArrayList<>();

        for (int i = 0; i < requirementList.size(); i++){
            requirementsName.add(requirementList.get(i).getName());
            requirementsAmount.add((double) requirementList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(requirementsName, requirementsAmount, total);

        model.addAttribute("requirementsName", requirementsName);
        model.addAttribute("requirementsAmount", requirementsAmount);
        return "requirements-popularity";
    }

}
