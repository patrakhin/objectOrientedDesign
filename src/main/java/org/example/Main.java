package org.example;

// Абстрактный класс Grandfather
abstract class Grandfather {
    abstract void familyRelationships(); // Родственные отношения
}

class Father extends Grandfather {
    @Override
    void familyRelationships() {
        System.out.println("Я - Отец.");
    }

    void fatherResponsibilities() {
        System.out.println("Выполняю свои обязанности как Отец.");
    }
}

class Son extends Father {
    @Override
    void familyRelationships() {
        System.out.println("Я - Сын.");
    }

    void sonResponsibilities() {
        System.out.println("Выполняю свои обязанности как Сын.");
    }
}

class Grandson extends Son {
    @Override
    void familyRelationships() {
        System.out.println("Я - Внук.");
    }

    void grandsonResponsibilities() {
        System.out.println("Выполняю свои обязанности как Внук.");
    }
}


public class Main {
    public static void main(String[] args) {
        Grandfather grandfather = new Grandson();
        grandfather.familyRelationships(); // Вызывает метод Внук.familyRelationships()

        Father father = new Son();
        father.familyRelationships(); // Вызывает метод Сын.familyRelationships()

        // Мы не можем напрямую обратиться к методам, свойственным Сыну или Внуку,
        // так как переменная father имеет статический тип Father.
        // father.sonResponsibilities(); // Ошибка компиляции
        // father.grandsonResponsibilities(); // Ошибка компиляции

        // Если мы хотим обратиться к методам Сына, нужно выполнить явное приведение типов.
        if (father instanceof Son) {
            Son son = (Son) father;
            son.sonResponsibilities();
        }
    }
}
