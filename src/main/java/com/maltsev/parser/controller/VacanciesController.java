package com.maltsev.parser.controller;

import com.maltsev.parser.entity.Vacancy;
import com.maltsev.parser.repository.VacancyRepository;
import com.maltsev.parser.service.intToDoubleConverter.MakeIntArrayToPercentDoubleArray;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class VacanciesController {

    public final VacancyRepository vacancyRepository;
    private static int total;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    public VacanciesController(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @GetMapping
    public String main(Model model) {
        List<Vacancy> vacancyList = vacancyRepository.findVacanciesByDateOrderByAmountDesc(formatter.format(date));
        List<String> vacancyName = new ArrayList<>();
        List<Double> vacancyAmount = new ArrayList<>();

        for (int i = 0; i < vacancyList.size(); i++){
            vacancyName.add(vacancyList.get(i).getName());
            vacancyAmount.add((double) vacancyList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(vacancyName, vacancyAmount, total);

        model.addAttribute("vacancies", vacancyName);
        model.addAttribute("vacanciesAmount", vacancyAmount);

        return "vacancies-popularity";
    }

    @GetMapping("/date")
    public String pickLanguagesStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                    Model model){
        List<Vacancy> vacancyList = vacancyRepository.findVacanciesByDateOrderByAmountDesc(chosenDate);
        List<String> vacancyName = new ArrayList<>();
        List<Double> vacancyAmount = new ArrayList<>();

        for (int i = 0; i < vacancyList.size(); i++){
            vacancyName.add(vacancyList.get(i).getName());
            vacancyAmount.add((double) vacancyList.get(i).getAmount());
        }

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(vacancyName, vacancyAmount, total);

        model.addAttribute("vacancies", vacancyName);
        model.addAttribute("vacanciesAmount", vacancyAmount);
        return "vacancies-popularity";
    }


}
