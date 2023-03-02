package com.maltsev.vacanciesparser.controller;

import com.maltsev.vacanciesparser.entity.Vacancy;
import com.maltsev.vacanciesparser.repository.VacancyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static com.maltsev.vacanciesparser.service.date.DateService.getFormattedDate;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    private final VacancyRepository vacancyRepository;
    private final Map<String, Integer> vacanciesMap = new LinkedHashMap<>();

    @Autowired
    public VacancyController(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @GetMapping
    public String getVacanciesRating(@RequestParam(value = "date", required = false) Optional<String> date,
                                     Model model) {
        List<Vacancy> vacancyList = date.map(vacancyRepository::findVacanciesByDateOrderByAmountDesc)
                .orElseGet(() -> vacancyRepository.findVacanciesByDateOrderByAmountDesc(getFormattedDate()));

        vacanciesMap.clear();
        buildVacancyMap(vacancyList);

        model.addAttribute("vacancies", vacanciesMap.keySet());
        model.addAttribute("amount", vacanciesMap.values());

        return "ratings-views/vacancies-popularity";
    }

    private void buildVacancyMap(List<Vacancy> vacancyList) {
        for (Vacancy vacancy : vacancyList) {
            vacanciesMap.put(vacancy.getName(), vacancy.getAmount());
        }
    }

}