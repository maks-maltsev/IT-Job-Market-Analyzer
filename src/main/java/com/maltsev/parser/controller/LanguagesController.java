package com.maltsev.parser.controller;

import com.maltsev.parser.repository.LanguageRepos;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String main(Model model) throws IOException {
        ArrayList<String> languages = languageRepos.selectLanguagesArray("2022-05");
        ArrayList<Integer> languageAmount = languageRepos.selectLanguagesAmountArray();

        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);

        return "languages-popularity";
    }

    @GetMapping("/{date}")
    public String pickLanguagesStatsByDate(@RequestParam(name = "date") String date,
                    Model model){
        ArrayList<String> languages = languageRepos.selectLanguagesArrayWhereDateIs(date);
        ArrayList<Integer> languageAmount = languageRepos.selectLanguagesAmountArrayWhereDateIs(date);

        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);
        return "languages-popularity";
    }


}
