package com.maltsev.vacancies_parser.entity.enums;

public enum RequirementsForChart {
    HTML ("HTML 5"),
    SQL ("SQL"),
    ORM ("ORM"),
    NOSQL ("NoSQL"),
    CSS ("CSS"),
    GIT ("GIT"),
    DOCKER ("Docker"),
    LINUX ("Linux system"),
    OOP ("OOP"),
    SOLID ("SOLID"),
    SOKENENGLISH ("Spoken English"),
    HTTP ("HTTP"),
    DNS ("DNS"),
    DESIGNPATTERNS ("Design Patterns"),
    SCRUM ("Scrum methodology"),
    AGILE ("Agile methodology"),
    KANBAN ("Kanban methodology"),
    ONEYEAROFEXP ("1+ years of experience"),
    TWOYEARSOFEXP ("2+ years of experience"),
    THREEYEARSOFEXP ("3+ years of experience"),
    FOURYEARSOFEXP ("4+ years of experience"),
    FIVEYEARSOFEXP ("5+ years of experience");

    private String title;

    RequirementsForChart(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static String[] getAllRequirementsForChart() {
        String[] requirementsForChart =
                new String[RequirementsForChart.values().length];
        int index = 0;
        for(RequirementsForChart requirements : RequirementsForChart.values()) {
            requirementsForChart[index++] = requirements.getTitle();
        }

        return requirementsForChart;
    }
}
