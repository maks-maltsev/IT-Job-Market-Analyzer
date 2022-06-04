package com.maltsev.parser.service.dataEnums;

/*
    хранение названий с точками и прочими символами в enum-ах слишком громоздкие,
    поэтому перечисления решил хранить в интерфейсе
*/

public interface IVacancies extends MyEnums {
    String [] vacancies = {"Java", "C#", "1C", "GO", "Scala", "JavaScript", "Php", "C++", "Python",
                           "Ruby", "TypeScript", "Dart", "Swift", "Kotlin", "PM", "QA", "Analyst",
                           "DevOps", "Designer", "iOS", "Mobile"};

}