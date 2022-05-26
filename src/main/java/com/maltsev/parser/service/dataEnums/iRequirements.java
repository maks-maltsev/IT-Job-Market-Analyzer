package com.maltsev.parser.service.dataEnums;

/*
    названия с точками и прочими символами в enum-ах слишком громоздкие,
    поэтому перечисления решил хранить в интерфейсе
*/

public interface iRequirements {
    String [] requirements = {"html", "sql", "orm", "english", "nosql", "css", "git", "docker", "linux"};
}
