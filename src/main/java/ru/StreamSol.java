package ru;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamSol {
    public static void main(String[] args) {

        //Фильтрация и преобразование: У вас есть список пользователей.
        //Необходимо отфильтровать пользователей, чей возраст больше 18 лет,
        //затем преобразовать их имена в верхний регистр и собрать результат в новый список.
        List<User> users = Arrays.asList(
                new User("John", 20),
                new User("Alice", 25),
                new User("Bob", 18)
        );
        List<String> filteredUsers = users.stream()
                .filter(user -> user.getAge() > 18)
                .map(user -> user.getName().toUpperCase())
                .toList();
        System.out.println("List's users " + filteredUsers);

        //Агрегация данных: У вас есть список заказов с товарами и их стоимостью.
        //Необходимо найти среднюю стоимость товаров в каждом заказе и вывести список средних стоимостей.
        List<Order> orders = Arrays.asList(
                new Order(Arrays.asList(new Product("Laptop", 1000), new Product("Phone", 500))),
                new Order(Arrays.asList(new Product("Headphones", 100), new Product("Tablet", 800)))
        );
        List<Double> listOfCost = orders.stream()
                .mapToDouble(order -> order.getProducts().stream().mapToInt(Product::getPrice).average().orElse(0.0))
                .boxed()
                .toList();
        System.out.println(" AVG cost for each order = " + listOfCost);

        //Группировка и подсчет: У вас есть список продуктов с их категориями.
        //Необходимо сгруппировать продукты по категориям и для каждой категории посчитать количество продуктов в ней.
        List<ProductTwo> productsTwo = Arrays.asList(
                new ProductTwo("Laptop", "Electronics"),
                new ProductTwo("Phone", "Electronics"),
                new ProductTwo("Book", "Books"),
                new ProductTwo("Headphones", "Electronics"),
                new ProductTwo("Tablet", "Electronics"),
                new ProductTwo("Keyboard", "Electronics"),
                new ProductTwo("Mouse", "Electronics"),
                new ProductTwo("Chair", "Furniture"),
                new ProductTwo("Desk", "Furniture")
        );
        Map<String, Long> groupByCategory = productsTwo.stream()
                .collect(Collectors.groupingBy(ProductTwo::getCategory, Collectors.counting()));
        System.out.println(" group by category " + groupByCategory);
        System.out.println(" group by category " + groupByCategory.values());

        //Фильтрация и преобразование:
        //Задача 1:
        // Отфильтровать список чисел так, чтобы остались только числа больше 10,
        //затем умножить каждое число на 2 и получить сумму всех результатов.
        List<Integer> numbers = Arrays.asList(5, 12, 8, 17, 6, 21);
        Double endResult = numbers.stream()
                .filter(num -> num > 10) //interim operation
                .mapToDouble(nums -> nums * 2)//interim operation
                .sum(); //terminal operation
        System.out.println(endResult);

        //Задача 2:
        // Из списка строк выбрать только те, которые начинаются с буквы "A"
        // и преобразовать их в верхний регистр.
        List<String> words = Arrays.asList("apple", "banana", "orange", "Ant", "apricot");
        List<String> resultList = words.stream()
                .filter(word -> word.startsWith("A"))
                .map(String::toUpperCase)
                .toList();
        System.out.println("result " + resultList);

        //Задача 3:
        //Отфильтровать список людей по возрасту > 20 и преобразовать их в другой тип объектов (вывести список имен).
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 20)
        );
        List<String> listOfName = people.stream() //interim operation
                .filter(men -> men.getAge() > 20)  //interim operation
                .map(Person::getName) //interim operation
                .toList(); //terminal operation
        System.out.println("New List " + listOfName);

        //Агрегация данных:
        //Задача 1:
        //Найти среднее значение списка чисел.
        List<Integer> numbersTwo = Arrays.asList(5, 10, 15, 20, 25);
        double endResultTwo = numbersTwo.stream()
                .mapToDouble(num -> num)
                .average() //terminal operation
                .orElse(0.0); //terminal operation
        System.out.println("average nums " + endResultTwo);

        //Задача 2:
        //Найти максимальное значение в списке строк по их длине.
        List<String> wordsTwo = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");
        int maxValue = wordsTwo.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        System.out.println("max value " + maxValue);

        //Задача 3:
        //Посчитать общую сумму стоимости всех продуктов в списке.
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1000),
                new Product("Smartphone", 800),
                new Product("Tablet", 500)
        );
        int totalPrice = products.stream()
                .mapToInt(Product::getPrice) //interim operation
                .sum(); //terminal operation
        System.out.println("total price " + totalPrice);

        //остортировать по возрастанию
        List<Integer> numbersThree = Arrays.asList(10, 5, 20, 25, 15);
        List<Integer> end = numbersThree.stream()
                .sorted().toList();
        System.out.println(end);

        //остортировать по убыванию
        List<Integer> numbersOne = Arrays.asList(10, 5, 20, 25, 15);
        List<Integer> endOne = numbersOne.stream()
                .sorted(Comparator.reverseOrder()).toList();
        System.out.println(endOne);

        //Группировка и подсчет:
        //Задача 1:
        //Подсчитать количество слов разной длины в списке строк.

        List<String> wordsOne = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");
        Map<Integer,Long> wordsCount = wordsOne.stream()
                        .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("result " + wordsCount);

        //Задача 2:
        //Сгруппировать объекты по возрастной группе и подсчитать количество объектов в каждой группе.

        List<Person> peopleOne = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25),
                new Person("David", 30)
        );
        Map<Integer,Long> numberOfPeople = peopleOne.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(" numbers of people " + numberOfPeople);

        //Задача 3:
        //Группировка элементов по длине их названия, а затем вывод только первых двух элементов из каждой группы.
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "kiwi", "grape", "melon");
        Map<Integer, List<String>> groupAndOut = fruits.stream()
                .collect(Collectors.groupingBy(String::length));
        groupAndOut.forEach((length, list) ->{
            List<String> firstTwo = list.stream().limit(2).toList();
            System.out.println("Слова длины " + length + ": " + firstTwo);
        });


    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Order {
    private List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}

class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

class ProductTwo {
    private String name;
    private String category;

    public ProductTwo(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}

class Person {
    private String name;
    private int age;

    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}