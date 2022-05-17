package com.maltsev.parser.service.vacanciesSite;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.maltsev.parser.service.programmingLanguage.iLanguages;
import com.maltsev.parser.service.requirements.iRequirements;

public class Test {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat formatter= new SimpleDateFormat("MMMM yyyy");
        Date date = new Date(System.currentTimeMillis());

        System.out.println(formatter.format(date));

    }
}
