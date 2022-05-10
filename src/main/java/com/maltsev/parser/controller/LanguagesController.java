package com.maltsev.parser.controller;

import com.maltsev.parser.model.Languages;
import com.maltsev.parser.repository.LanguageRepos;
import com.maltsev.parser.service.programmingLanguage.ILanguages;
import com.maltsev.parser.service.vacanciesSite.DjinniCo;
import com.maltsev.parser.service.vacanciesSite.JobsUa;
import com.maltsev.parser.service.vacanciesSite.WorkUa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class LanguagesController {
    @Autowired
    public LanguageRepos languageRepos;

    SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/")
    public String main(Model model) throws IOException {
        ArrayList<String> languages = languageRepos.selectLanguagesArray();
        ArrayList<Integer> languageAmount = languageRepos.selectLanguagesAmountArray();

        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);

        return "languages-popularity";
    }


}
