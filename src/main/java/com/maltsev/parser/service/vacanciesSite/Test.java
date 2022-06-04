package com.maltsev.parser.service.vacanciesSite;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class Test {
    public static void main(String[] args) {
        Parser parser = new Parser(new JobsUa());
        Set<String> descriptionsSet = new HashSet<>();
        try {
            descriptionsSet = parser.returnAllDescriptions();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(descriptionsSet.size());
    }
}
