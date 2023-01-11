package com.maltsev.parser.service.dataEnums;

/*
    хранение названий с точками и прочими символами в enum-ах слишком громоздкие,
    поэтому перечисления решил хранить в интерфейсе
*/

public interface IRequirements {

    String [] requirementsForChart = {"HTML 5", "SQL", "ORM", "NoSQL", "CSS", "GIT", "Docker", "Linux system", "OOP", "SOLID",
            "Spoken English", "HTTP", "DNS", "Design Patterns",
            "Scrum methodology", "Agile methodology", "Kanban methodology", "1+ years of experience", "2+ years of experience",
            "3+ years of experience", "4+ years of experience", "5+ years of experience"};
}
