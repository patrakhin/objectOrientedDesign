package org.example;

import java.util.ArrayList;
import java.util.List;

class Animal {
    void makeSound() {
        System.out.println("Some generic sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow");
    }
}

public class CovarianceExample {
    public static void main(String[] args) {
        // Ковариантность с использованием списков
        List<? extends Animal> animals;

        // Создаем список кошек
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());

        // Кладем список кошек в список животных
        animals = cats;

        // Можем читать животных из списка без явного приведения типов
        Animal animal = animals.get(0);
        animal.makeSound();  // Выведет "Мяу"
    }
}
