package com.maltsev.vacancies_parser.controller;

import com.maltsev.vacancies_parser.entity.Requirement;
import com.maltsev.vacancies_parser.repository.RequirementRepository;
import com.maltsev.vacancies_parser.service.date_manager.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RequirementController {

    private final RequirementRepository requirementRepository;
    private Map<String, Double> requirementsMap = new LinkedHashMap<>();

    @Autowired
    public RequirementController(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @GetMapping("/requirements")
    public String requirementsPage (Model model){
        List<Requirement> requirementList = requirementRepository.
                findRequirementsByDateOrderByAmountDesc(DateService.getFormattedDate());

        for (Requirement requirement : requirementList){
            requirementsMap.put(requirement.getName(), (double) requirement.getAmount());
        }

        model.addAttribute("requirementsName", requirementsMap.keySet());
        model.addAttribute("requirementsAmount", requirementsMap.values());

        return "ratings-views/requirements-popularity";
    }

    @GetMapping("/requirements-date")
    public String pickRequirementsStatsByDate(
            @RequestParam("chosenDate") String chosenDate,
            Model model){

        List<Requirement> requirementList = requirementRepository
                .findRequirementsByDateOrderByAmountDesc(chosenDate);

        if(requirementList.isEmpty()) {
            return "redirect:/requirements";
        }

        for (Requirement requirement : requirementList){
            requirementsMap.put(requirement.getName(), (double) requirement.getAmount());
        }

        model.addAttribute("requirementsName", requirementsMap.keySet());
        model.addAttribute("requirementsAmount", requirementsMap.values());

        return "ratings-views/requirements-popularity";
    }

}
