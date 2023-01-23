package com.maltsev.parser.entity.enums;

public enum Vacancies {
    JAVA ("Java"),
    CSHARP ("C#"),
    ONEC ("1C"),
    GO ("GO"),
    SCALA ("Scala"),
    JAVASCRIPT ("JavaScript"),
    PHP ("Php"),
    CPP ("C++"),
    PYTHON ("Python"),
    RUBY ("Ruby"),
    TYPESCRIPT ("TypeScript"),
    DART ("Dart"),
    SWIFT ("Swift"),
    KOTLIN ("Kotlin"),
    PM ("PM"),
    QA ("QA"),
    ANALYST ("Analyst"),
    DEVOPS ("DevOps"),
    DESIGNER ("Designer"),
    IOS ("iOS"),
    ANDROID ("Android"),
    TEAMLEAD ("Team Lead"),
    ADMINISTRATOR ("Administrator"),
    TECHLEAD ("Tech Lead");

    private String title;

    private String getTitle() {
        return title;
    }

    Vacancies(String title) {
        this.title = title;
    }

    public static String[] getVacanciesArray() {

        String[] vacancies = new String[Vacancies.values().length];
        int index = 0;
        for (Vacancies vacancy : Vacancies.values()) {
            vacancies[index++] = vacancy.title;
        }
        return vacancies;
    }
}
