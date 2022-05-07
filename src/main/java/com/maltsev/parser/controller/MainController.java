package com.maltsev.parser.controller;

import com.maltsev.parser.model.Languages;
import com.maltsev.parser.repository.LanguageRepos;
import com.maltsev.parser.service.programmingLanguage.Language;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    public LanguageRepos languageRepos;

    SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/")
    public String main(Model model) throws IOException {
        WorkUa workUa = new WorkUa();
        DjinniCo djinniCo = new DjinniCo();
        JobsUa jobsUa = new JobsUa();

        Set<String> workUaSet = workUa.selectAllVacancies(workUa.getSiteLink());
        Set<String> djinnoCoSet = djinniCo.selectAllVacancies(djinniCo.getSiteLink());
        Set<String> jobsUaSet = jobsUa.selectAllVacancies(jobsUa.getSiteLink());

        Set<String> all = new HashSet<>();

        all.addAll(workUaSet);
        all.addAll(djinnoCoSet);
        all.addAll(jobsUaSet);

        System.out.println(all.size());


        Language language = new Language();
        String [] languages = language.getLanguages();
        int [] languageAmount = new int[languages.length];

        for(int j = 0; j < languages.length; j++){
            languageAmount[j] = workUa.vacanciesCounter(languages[j], all );
        }
        model.addAttribute("languages", languages);
        model.addAttribute("languageAmount", languageAmount);

        return "home";
    }

    @GetMapping("/refresh/table")
    public String refreshTable(){
        return "refresh-table";
    }

    @PostMapping("/refresh/table")
    public String languagesAdd() throws IOException {

        WorkUa workUa = new WorkUa();
        DjinniCo djinniCo = new DjinniCo();
        JobsUa jobsUa = new JobsUa();

        Set<String> workUaSet = workUa.selectAllVacancies(workUa.getSiteLink());
        Set<String> djinnoCoSet = djinniCo.selectAllVacancies(djinniCo.getSiteLink());
        Set<String> jobsUaSet = jobsUa.selectAllVacancies(jobsUa.getSiteLink());

        Set<String> all = new HashSet<>();

        all.addAll(workUaSet);
        all.addAll(djinnoCoSet);
        all.addAll(jobsUaSet);

        System.out.println(all.size());


        Language language = new Language();
        String [] languages = language.getLanguages();
        int [] languageAmount = new int[languages.length];

        for(int j = 0; j < languages.length; j++){
            languageAmount[j] = workUa.vacanciesCounter(languages[j], all );
        }



        /*for(int j = 0; j < languages.length; j++){
            Languages lang = new Languages(languages[j], languageAmount[j], formatter.format(date));
            languageRepos.save(lang);
        }

        int total = 0;
        for (int i: languageAmount) {
            total+=i;
        }

        System.out.println(total);
*/
        return "redirect:/";
    }

}
