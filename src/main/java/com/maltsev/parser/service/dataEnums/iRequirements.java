package com.maltsev.parser.service.dataEnums;

/*
    хранение названий с точками и прочими символами в enum-ах слишком громоздкие,
    поэтому перечисления решил хранить в интерфейсе
*/

public interface IRequirements extends MyEnums {
    String [] requirements = {"HTML", "SQL", "ORM", "NoSQL", "CSS", "GIT", "Docker", "Linux", "Senior", "Junior", "Middle"};
}
