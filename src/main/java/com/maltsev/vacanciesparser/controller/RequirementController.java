package com.maltsev.vacanciesparser.controller;

import com.maltsev.vacanciesparser.entity.Requirement;
import com.maltsev.vacanciesparser.repository.RequirementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static com.maltsev.vacanciesparser.service.date.DateService.getFormattedDate;

@Controller
@RequestMapping("/requirements")
public class RequirementController {

    private final RequirementRepository requirementRepository;
    private final Map<String, Integer> requirementsMap = new LinkedHashMap<>();

    @Autowired
    public RequirementController(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @GetMapping
    public String getRequirementsRating(@RequestParam(value = "date", required = false) Optional<String> date,
                                      Model model) {
        List<Requirement> requirementList = date.map(requirementRepository::findRequirementsByDateOrderByAmountDesc)
                .orElseGet(() -> requirementRepository.findRequirementsByDateOrderByAmountDesc(getFormattedDate()));

        requirementsMap.clear();
        buildRequirementsMap(requirementList);

        model.addAttribute("requirement", requirementsMap.keySet());
        model.addAttribute("amount", requirementsMap.values());

        return "ratings-views/requirements-popularity";
    }

    private void buildRequirementsMap(List<Requirement> requirementList) {
        for (Requirement requirement : requirementList) {
            requirementsMap.put(requirement.getName(), requirement.getAmount());
        }
    }

}
