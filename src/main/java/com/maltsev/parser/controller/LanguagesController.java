package com.maltsev.parser.controller;

import com.maltsev.parser.repository.LanguageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class LanguagesController {
    @Autowired
    public LanguageRepos languageRepos;
    private static int total;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/")
    public String main(Model model) {
        ArrayList<String> languages = languageRepos.selectLanguagesArrayWhereDateIs(formatter.format(date));
        ArrayList<Double> languageAmount = languageRepos.selectLanguagesAmountArrayWhereDateIs(formatter.format(date));

        FunctionsForAllControllers.makeIntArrayToPercentDouble(languages, languageAmount, total);

        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);

        return "languages-popularity";
    }

    @GetMapping("/languagesDate")
    public String pickLanguagesStatsByDate(@RequestParam(value = "chosenDate", required = false, defaultValue = "2022-05") String chosenDate,
                    Model model){
        ArrayList<String> languages = languageRepos.selectLanguagesArrayWhereDateIs(chosenDate);
        ArrayList<Double> languageAmount = languageRepos.selectLanguagesAmountArrayWhereDateIs(chosenDate);

        FunctionsForAllControllers.makeIntArrayToPercentDouble(languages, languageAmount, total);

        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);
        return "languages-popularity";
    }


}
