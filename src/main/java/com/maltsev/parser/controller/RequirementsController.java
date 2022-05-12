package com.maltsev.parser.controller;

import com.maltsev.parser.model.Frameworks;
import com.maltsev.parser.model.Languages;
import com.maltsev.parser.model.Requirements;
import com.maltsev.parser.repository.FrameworksRepos;
import com.maltsev.parser.repository.LanguageRepos;
import com.maltsev.parser.repository.RequirementsRepos;
import com.maltsev.parser.service.frameworks.iFrameworks;
import com.maltsev.parser.service.programmingLanguage.iLanguages;
import com.maltsev.parser.service.requirements.iRequirements;
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
public class RequirementsController {
    @Autowired
    FrameworksRepos frameworksRepos;
    @Autowired
    LanguageRepos languageRepos;
    @Autowired
    RequirementsRepos requirementsRepos;

    SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date(System.currentTimeMillis());

    @GetMapping("/requirements")
    public String requirementsPage (Model model){
        ArrayList<String> requirementsName = requirementsRepos.selectRequirementsArray();
        ArrayList<Integer> requirementsAmount = requirementsRepos.selectRequirementsAmountArray();

        model.addAttribute("requirementsName", requirementsName);
        model.addAttribute("requirementsAmount", requirementsAmount);
        return "requirements-popularity";
    }

    @PostMapping("/requirements/refresh-languages")
    public String refreshLanguages() throws IOException, InterruptedException{

            WorkUa workUa = new WorkUa();
            DjinniCo djinniCo = new DjinniCo();
            JobsUa jobsUa = new JobsUa();

            Set<String> workUaSet = workUa.selectVacanciesTitles(workUa.getSiteLink());
            Set<String> djinnoCoSet = djinniCo.selectVacanciesTitles(djinniCo.getSiteLink());
            Set<String> jobsUaSet = jobsUa.selectVacanciesTitles(jobsUa.getSiteLink());

            Set<String> allTitles = new HashSet<>();

            allTitles.addAll(workUaSet);
            allTitles.addAll(djinnoCoSet);
            allTitles.addAll(jobsUaSet);

            String [] languages = iLanguages.languages;
            int [] languageAmount = new int[languages.length];

            for(int j = 0; j < languages.length; j++){
                languageAmount[j] = workUa.vacanciesCounter(languages[j], allTitles );
            }

            for(int j = 0; j < languages.length; j++){
                Languages lang = new Languages(languages[j], languageAmount[j], formatter.format(date));
                languageRepos.save(lang);
            }

            int total = 0;
            for (int i: languageAmount) {
                total+=i;
            }
            System.out.println(total);

            return "redirect:/";

    }

    @PostMapping("/requirements/refresh-frameworks")
    public String refreshFrameworks() throws IOException, InterruptedException {

        WorkUa workUa = new WorkUa();
        DjinniCo djinniCo = new DjinniCo();
        JobsUa jobsUa = new JobsUa();

        Set<String> workUaSet = workUa.selectDescriptions(workUa.getSiteLink());
        Set<String> djinnoCoSet = djinniCo.selectDescriptions(djinniCo.getSiteLink());
        Set<String> jobsUaSet = jobsUa.selectDescriptions(jobsUa.getSiteLink());

        Set<String> allDescriptions = new HashSet<>();

        allDescriptions.addAll(workUaSet);
        allDescriptions.addAll(djinnoCoSet);
        allDescriptions.addAll(jobsUaSet);

        System.out.println(allDescriptions.size());

        String [] frameworks = iFrameworks.frameworks;
        int [] frameworkAmount = new int[frameworks.length];

        for(int j = 0; j < frameworks.length; j++){
            frameworkAmount[j] = workUa.frameworksCounter(frameworks[j], allDescriptions );
        }

        for(int j = 0; j < frameworks.length; j++){
            Frameworks frmwork = new Frameworks(frameworks[j], frameworkAmount[j], formatter.format(date));
            frameworksRepos.save(frmwork);
        }

        int total = 0;
        for (int i: frameworkAmount) {
            total+=i;
        }
        System.out.println(total);


        return "redirect:/frameworks";
    }

    @PostMapping("/requirements/refresh-requirements")
    public String refreshRequirements() throws IOException, InterruptedException {

        WorkUa workUa = new WorkUa();
        DjinniCo djinniCo = new DjinniCo();
        JobsUa jobsUa = new JobsUa();

        Set<String> workUaSet = workUa.selectDescriptions(workUa.getSiteLink());
        Set<String> djinnoCoSet = djinniCo.selectDescriptions(djinniCo.getSiteLink());
        Set<String> jobsUaSet = jobsUa.selectDescriptions(jobsUa.getSiteLink());

        Set<String> allDescriptions = new HashSet<>();

        allDescriptions.addAll(workUaSet);
        allDescriptions.addAll(djinnoCoSet);
        allDescriptions.addAll(jobsUaSet);

        System.out.println(allDescriptions.size());

        String [] requirements = iRequirements.requirements;
        int [] requirementsAmount = new int[requirements.length];

        for(int j = 0; j < requirements.length; j++){
            requirementsAmount[j] = workUa.frameworksCounter(requirements[j], allDescriptions );
        }

        for(int j = 0; j < requirements.length; j++){
            Requirements requirements1 = new Requirements(requirements[j], requirementsAmount[j], formatter.format(date));
            requirementsRepos.save(requirements1);
        }

        int total = 0;
        for (int i: requirementsAmount) {
            total+=i;
        }
        System.out.println(total);

        return "redirect:/requirements";
    }
}
