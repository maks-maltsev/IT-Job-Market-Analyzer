package com.maltsev.parser.service.dataCounters;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FrameworkCounterByNameTest {

    @Test
    public void frameworksCounterShouldIncrementIfElementInListHaveFrameworkName(){
        int counter = 0;
        Set<String> descriptionsSet = new HashSet<>();
        descriptionsSet.add("Needed java developer");
        descriptionsSet.add("Project manager vacancy");
        descriptionsSet.add("Junior Java QA");
        descriptionsSet.add("HR vacancy");
        String frameworkName = "java";
        int expectedValue = 2;

        List<String> descriptionsList = new ArrayList<>();
        descriptionsList.addAll(descriptionsSet);
        for(int i = 0; i < descriptionsList.size(); i++){
            if(descriptionsList.get(i).toLowerCase().contains(frameworkName)){
                counter++;
            }
        }
        Assert.assertEquals(expectedValue, counter);
    }

}
