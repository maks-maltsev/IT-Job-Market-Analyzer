package com.maltsev.parser.service.vacanciesSite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

@Setter
@Getter
@ToString
public abstract class Site {

   private Set<String> vacanciesSet = new HashSet<>();
   // private HashMap<String, Integer> hashMap = new HashMap<>();

    public abstract Set<String> selectAllVacancies(String siteLink) throws IOException;

   public int vacanciesCounter(String name, Set<String> set){
        int counter = 0;
        List<String> vacanciesList = new ArrayList<>();
        vacanciesList.addAll(set);

        for(int i = 0; i < vacanciesList.size(); i++){
            if(vacanciesList.get(i).toLowerCase().contains(name + " ") || vacanciesList.get(i).toLowerCase().contains(name + "-")
                    || vacanciesList.get(i).toLowerCase().contains(name + ",") || vacanciesList.get(i).toLowerCase().contains(name + ".")){
                counter++;
            }

        }

        return counter;
    }

}
