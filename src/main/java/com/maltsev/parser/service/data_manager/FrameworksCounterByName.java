package com.maltsev.parser.service.data_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FrameworksCounterByName {

    public static int frameworksCounter(String frameworkName, Set<String> descriptionsSet){
        int counter = 0;

        List<String> descriptionsList = new ArrayList<>();
        descriptionsList.addAll(descriptionsSet);
        for(int i = 0; i < descriptionsList.size(); i++){
            if(descriptionsList.get(i).toLowerCase().contains(frameworkName.toLowerCase())){
                counter++;
            }
        }
        System.out.println(frameworkName + " : " + counter);
        return counter;
    }
}
