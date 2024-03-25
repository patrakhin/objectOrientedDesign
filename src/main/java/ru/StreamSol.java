package ru;

import java.util.Arrays;
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