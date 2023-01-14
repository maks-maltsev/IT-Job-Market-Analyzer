package com.maltsev.parser.entity.enums;

public enum VacanciesForChart {

    JAVA_DEV ("Java developer"),
    C_SHARP_DEV ("C# developer"),
    ONE_C_DEV ("1C developer"),
    GO_LANG_DEV ("GOlang developer"),
    SCALA_DEV ("Scala developer"),
    JAVASCRIPT_DEV ("JavaScript developer"),
    PHP_DEV ("Php developer"),
    CPP_DEV ("C++ developer"),
    PYTHON_DEV ("Python developer"),
    RUBY_DEV ("Ruby developer"),
    TYPESCRIPT_DEV ("TypeScript developer"),
    DART_DEV ("Dart developer"),
    SWIFT_DEV ("Swift developer"),
    KOTLIN_DEV ("Kotlin developer"),
    PROJECT_MANAGER ("Project Manager"),
    QA ("QA"),
    DATA_ANALYST ("Data Analyst"),
    DEVOPS ("DevOps"),
    DESIGNER ("Designer"),
    IOS_DEV ("iOS dev"),
    ANDROID_DEV ("Android dev"),
    TEAM_LEAD ("Team Lead"),
    ADMINISTRATOR ("Administrator"),
    TECH_LEAD ("Tech Lead");

    private String title;

    VacanciesForChart(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static String[] getAllVacanciesForChart() {
        String[] vacanciesForChart =
                new String[VacanciesForChart.values().length];
        int index = 0;
        for(VacanciesForChart vacancies : VacanciesForChart.values()) {
            vacanciesForChart[index++] = vacancies.getTitle();
        }
        return vacanciesForChart;
    }
}
