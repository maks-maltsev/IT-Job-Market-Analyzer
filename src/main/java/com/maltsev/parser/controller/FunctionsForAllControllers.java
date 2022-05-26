package com.maltsev.parser.controller;

import lombok.Getter;

import java.util.List;

public class FunctionsForAllControllers {

    public static void makeIntArrayToPercentDouble(List<String> languages, List<Double> languageAmount, int total){
        for (int i = 0; i < languages.size(); i++){
            total+= languageAmount.get(i);
        }
        for (int i = 0; i < languages.size(); i++){
            double x = 100 * languageAmount.get(i) / total;
            languageAmount.set(i, x);
        }
    }

}
