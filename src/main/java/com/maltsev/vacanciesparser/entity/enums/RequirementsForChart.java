package com.maltsev.vacanciesparser.entity.enums;

/**
 This enum represents the requirements for chart data for vacancies.
 */
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
    JIRA ("Jira"),
    JUNIOR ("Junior developer"),
    MIDDLE ("Middle developer"),
    SENIOR ("Senior developer"),
    PRE_INTERMEDIATE ("Pre-intermediate eng. (A2)"),
    INTERMEDIATE ("Intermediate eng. (B1)"),
    UPPER_INTERMEDIATE ("Upper-intermediate eng. (B2)"),
    ADVANCED ("Advanced eng. (C1)");

    private final String title;

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
