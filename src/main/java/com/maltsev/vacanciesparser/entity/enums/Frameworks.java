package com.maltsev.vacanciesparser.entity.enums;

/**
 *
 * Enum representing popular web development frameworks in the IT industry.
 */
public enum Frameworks {

    DOTNET (".Net"),
    SPRING ("Spring"),
    REACT ("React"),
    JQUERY ("jQuery"),
    EXPRESS ("Express"),
    ANGULAR ("Angular"),
    VUEJS ("Vue.js"),
    FLASK ("Flask"),
    DJANGO ("Django"),
    LARAVEL ("Laravel"),
    RUBYONRAILS ("Ruby on Rails"),
    GATSBY ("Gatsby"),
    NODEJS ("Node.js"),
    YII ("Yii"),
    ZEND ("Zend"),
    JSF ("JSF"),
    ASPDOTNET ("ASP.NET"),
    KUBE ("Kube"),
    EMBER ("Ember");

    private final String title;

    Frameworks(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static String[] getFrameworksArray() {
        String[] frameworksArray = new String[Frameworks.values().length];
        int index = 0;

        for(Frameworks framework : Frameworks.values()) {
            frameworksArray[index++] = framework.title;
        }

        return frameworksArray;
    }

}
