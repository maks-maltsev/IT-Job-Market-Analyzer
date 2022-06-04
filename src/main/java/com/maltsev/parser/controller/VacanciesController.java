package com.maltsev.parser.controller;

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

@Controller
public class VacanciesController {
    @Autowired
    public VacanciesRepos vacanciesRepos;
    private static int total;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/")
    public String main(Model model) {
        ArrayList<String> languages = vacanciesRepos.selectLanguagesArrayWhereDateIs(formatter.format(date));
        ArrayList<Double> languageAmount = vacanciesRepos.selectLanguagesAmountArrayWhereDateIs(formatter.format(date));

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(languages, languageAmount, total);

        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);

        return "vacancies-popularity";
    }

    @GetMapping("/languagesDate")
    public String pickLanguagesStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                    Model model){
        ArrayList<String> languages = vacanciesRepos.selectLanguagesArrayWhereDateIs(chosenDate);
        ArrayList<Double> languageAmount = vacanciesRepos.selectLanguagesAmountArrayWhereDateIs(chosenDate);

        MakeIntArrayToPercentDoubleArray.makeIntArrayToPercentDouble(languages, languageAmount, total);

        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);
        return "vacancies-popularity";
    }


}
