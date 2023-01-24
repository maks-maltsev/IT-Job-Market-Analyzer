package com.maltsev.vacancies_parser.controller;

import com.maltsev.vacancies_parser.entity.Vacancy;
import com.maltsev.vacancies_parser.repository.VacancyRepository;
import com.maltsev.vacancies_parser.service.date_manager.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class VacancyController {

    public final VacancyRepository vacancyRepository;
    private Map<String, Double> vacanciesMap = new LinkedHashMap<>();

    @Autowired
    public VacancyController(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @GetMapping("/vacancies")
    public String showVacanciesRating(Model model) {

        List<Vacancy> vacancyList = vacancyRepository
                .findVacanciesByDateOrderByAmountDesc(DateService.getFormattedDate());

        for (Vacancy vacancy : vacancyList){
            vacanciesMap.put(vacancy.getName(), (double) vacancy.getAmount());
        }

        model.addAttribute("vacancies", vacanciesMap.keySet());
        model.addAttribute("vacanciesAmount", vacanciesMap.values());

        return "ratings-views/vacancies-popularity";
    }

    @GetMapping("/vacancies-date")
    public String showVacanciesRatingByDate(
                    @RequestParam("chosenDate") String chosenDate,
                    Model model) {

        List<Vacancy> vacancyList = vacancyRepository
                .findVacanciesByDateOrderByAmountDesc(chosenDate);

        if(vacancyList.isEmpty()) {
            return "redirect:/vacancies";
        }

        for (Vacancy vacancy : vacancyList){
           vacanciesMap.put(vacancy.getName(), (double) vacancy.getAmount());
        }

        model.addAttribute("vacancies", vacanciesMap.keySet());
        model.addAttribute("vacanciesAmount", vacanciesMap.values());

        return "ratings-views/vacancies-popularity";
    }

}
