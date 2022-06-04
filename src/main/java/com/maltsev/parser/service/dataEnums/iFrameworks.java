package com.maltsev.parser.service.dataEnums;

/*
    хранение названий с точками и прочими символами в enum-ах слишком громоздкие,
    поэтому перечисления решил хранить в интерфейсе
*/

public interface IFrameworks extends MyEnums {
    String[] frameworks = {".Net", "Spring", "React", "jQuery", "Express", "Angular",
    "Vue.js", "Flask", "Django", "Laravel", "Ruby on Rails", "Gatsby", "Node.js"};
}
