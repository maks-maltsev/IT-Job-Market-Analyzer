package com.maltsev.parser.service.vacanciesSite;

import java.io.IOException;

public class Parser {
    private AbstractSite abstractSite;

    public Parser(AbstractSite abstractSite){
        this.abstractSite = abstractSite;
    }

    public void d(){
        try {
            abstractSite.selectVacanciesTitles(abstractSite.getSiteLink());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
