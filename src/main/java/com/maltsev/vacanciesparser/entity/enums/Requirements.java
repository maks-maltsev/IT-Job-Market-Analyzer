package com.maltsev.vacanciesparser.entity.enums;

/**
 * This enum represents the list of possible requirements for a job vacancy in the IT industry.
 * The requirements listed in the enum are commonly requested in job vacancies and are often used to filter and search
 * for relevant job postings.
 */
public enum Requirements {

    HTML ("HTML"),
    SQL ("SQL"),
    ORM ("ORM"),
    NOSQL ("NoSQL"),
    CSS ("CSS"),
    GIT ("GIT"),
    DOCKER ("Docker"),
    LINUX ("Linux"),
    OOP ("OOP"),
    SOLID ("SOLID"),
    SPOKENENGLISH ("Spoken English"),
    HTTP ("HTTP"),
    DNS ("DNS"),
    PATTERNS ("Patterns"),
    SCRUM ("Scrum"),
    AGILE ("Agile"),
    KANBAN ("Kanban"),
    ONEYEAR ("1+"),
    TWOYEARS ("2+"),
    THREEYEARS ("3+"),
    FOURYEARS ("4+"),
    FIFEYEARS ("5+");

    private final String title;

    public String getTitle() {
        return title;
    }

    Requirements(String title) {
        this.title = title;
    }

    public static String[] getRequirementsArray() {
        String[] requirementsArray = new String[Requirements.values().length];
        int index = 0;

        for(Requirements requirement : Requirements.values()) {
            requirementsArray[index++] = requirement.title;
        }

        return requirementsArray;
    }

}
