package com.maltsev.parser.controller;

import com.maltsev.parser.model.Vacancies;
import com.maltsev.parser.repository.VacanciesRepos;
import com.maltsev.parser.service.intToDoubleConverter.MakeIntArrayToPercentDoubleArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class VacanciesController {

    @Autowired
    public VacanciesRepos vacanciesRepos;

    private static int total;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/")
    public String main(Model model) {
        List<Vacancies> vacanciesList = vacanciesRepos.findVacanciesByDateOrderByAmountDesc(formatter.format(date));
        List<String> vacancyName = new ArrayList<>();
        List<Double> vacancyAmount = new ArrayList<>();

        for (int i = 0; i < vacanciesList.size(); i++){
            vacancyName.add(vacanciesList.get(i).getName());
            vacancyAmount.add((double) vacanciesList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(vacancyName, vacancyAmount, total);

        model.addAttribute("vacancies", vacancyName);
        model.addAttribute("vacanciesAmount", vacancyAmount);

        return "vacancies-popularity";
    }

    @GetMapping("/vacanciesDate")
    public String pickLanguagesStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                    Model model){
        List<Vacancies> vacanciesList = vacanciesRepos.findVacanciesByDateOrderByAmountDesc(chosenDate);
        List<String> vacancyName = new ArrayList<>();
        List<Double> vacancyAmount = new ArrayList<>();

        for (int i = 0; i < vacanciesList.size(); i++){
            vacancyName.add(vacanciesList.get(i).getName());
            vacancyAmount.add((double) vacanciesList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(vacancyName, vacancyAmount, total);

        model.addAttribute("vacancies", vacancyName);
        model.addAttribute("vacanciesAmount", vacancyAmount);
        return "vacancies-popularity";
    }


}
