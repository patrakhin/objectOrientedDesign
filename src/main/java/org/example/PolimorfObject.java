package org.example;

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

public class PolimorfObject {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        myDog.makeSound();  // Динамическое связывание, вызывается метод makeSound() класса Dog
        myCat.makeSound();  // Динамическое связывание, вызывается метод makeSound() класса Cat
    }
}
