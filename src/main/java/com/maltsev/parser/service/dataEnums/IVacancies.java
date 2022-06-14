package com.maltsev.parser.service.dataEnums;

/*
    хранение названий с точками и прочими символами в enum-ах слишком громоздкие,
    поэтому перечисления решил хранить в интерфейсе
*/

public interface IVacancies extends MyEnums {
    String [] vacancies = {"Java", "C#", "1C", "GO", "Scala", "JavaScript", "Php", "C++", "Python",
                           "Ruby", "TypeScript", "Dart", "Swift", "Kotlin", "PM", "QA", "Analyst",
                           "DevOps", "Designer", "iOS", "Android", "Team Lead", "Administrator", "Tech Lead"};

    String [] vacanciesForChart = {"Java developer", "C# developer", "1C developer", "GOlang developer", "Scala developer", "JavaScript developer",
            "Php developer", "C++ developer", "Python developer",
            "Ruby developer", "TypeScript developer", "Dart developer", "Swift developer", "Kotlin developer", "Project Manager", "QA", "Data Analyst",
            "DevOps", "Designer", "iOS dev", "Android dev", "Team Lead", "Administrator", "Tech Lead"};
}