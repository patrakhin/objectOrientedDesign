import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


public class StreamChatGPT {
    public static void main(String[] args) {
        List<Integer> baseList = Arrays.asList(1,2,3,4,5);
        List<Integer> result = baseList.stream()
                .filter(integer -> integer % 2 != 0)
                .toList();
        result.forEach(System.out::println);

        List<String> baseString = Arrays.asList("apple", "banana", "grape", "melon", "peach");
        List<String> resultString = baseString.stream()
                .filter(s->s.contains("n"))
                .toList();
        resultString.forEach(System.out::println);

        List<Integer> intList = Arrays.asList(0,1,2,3,4,5,6);
        List<Integer> filtResult = intList.stream()
                .filter(integer -> integer > 3)
                .toList();
        filtResult.forEach(System.out::println);

        List<String> hipString = Arrays.asList("apple", "banana", "grape", "melon", "peach");
        List<String> sortHip = hipString.stream()
                .filter(string -> string.contains("e"))
                .map(string -> string.replace('e','X'))
                .toList();
        sortHip.forEach(System.out::println);

        List<Integer> intList_2 = Arrays.asList(1,2,3,4,5);
        List<Integer> resultInteger = intList_2.stream()
                .map(integer -> integer * 2)
                .toList();
        resultInteger.forEach(System.out::println);

        List<String> rawList = Arrays.asList("1", "2", "3", "4", "5");
        List<Integer> resultTransform = rawList.stream()
                .map(Integer::parseInt)
                .toList();
        resultTransform.forEach(System.out::println);

        List<String> transList = Arrays.asList("1", "2", "3", "4", "5");
        List<Integer> endList = transList.stream()
                .map(Integer::parseInt)
                .filter(integer -> integer % 2 == 0)
                .toList();
        endList.forEach(System.out::println);

        List<String> redList = Arrays.asList("1", "2", "3", "4", "5");
        int redSum = redList.stream()
                .map(Integer::parseInt)
                .reduce(1,(a,b) -> a*b);
        System.out.println("sum = " + redSum);


        List<String> prices = Arrays.asList("10.5 USD", "15 EUR", "20 USD", "12 EUR");
        // Шаг 1: Используйте map для преобразования в числовые значения
        List<Double> dollarPrices = prices.stream()
                .filter(price -> price.endsWith("USD"))
                .map(price -> Double.parseDouble(price.split(" ")[0])) // Получаем числовое значение

                .toList(); // Преобразуем результат в список

        // Шаг 2: Вычисляем среднее значение только для цен в долларах
        double averageDollarPrice = dollarPrices.stream()
                .reduce(Double::sum) // Суммируем все цены в долларах
                .map(total -> total / dollarPrices.size()) // Вычисляем среднее значение
                .orElse(0.0); // Возвращаем 0.0, если список пуст

        System.out.println("Средняя цена в долларах: " + averageDollarPrice);

        List<String> words = Arrays.asList("apple", "banana", "grape", "melon", "peach");
        double resultCalc = words.stream()
                .filter(word -> word.length() > 3)
                .mapToDouble(word -> Math.pow(word.length(), 2))
                .reduce(0.0, Double::sum);
        System.out.println("RESULT = " + resultCalc);

        List<List<String>> listOfList = Arrays.asList(
                Arrays.asList("apple", "banana", "orange"),
                Arrays.asList("cat", "dog"),
                Arrays.asList("java", "python", "ruby")
        );
        List<String> flattenedList = listOfList.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("FlatList = " + flattenedList);

        List<List<Integer>> startListOfNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(4, 5, 6, 7, 8),
                Arrays.asList(7, 8, 9, 10)
        );
        Optional<Integer> maxNumber = startListOfNumbers.stream()
                .flatMap(List::stream)
                .distinct()
                .max(Comparator.naturalOrder());
        maxNumber.ifPresent(value -> System.out.println("Max Number = " + value));


        List<List<String>> listOfWords = Arrays.asList(
                Arrays.asList("apple", "banana", "orange"),
                Arrays.asList("cat", "dog"),
                Arrays.asList("java", "python", "ruby")
        );
        Long numberOfUniqueWords = listOfWords.stream()
                .flatMap(List::stream)
                .distinct()
                .count();

        System.out.println("Количество уникальных слов: " + numberOfUniqueWords);


        List<ObjectWithColors> objectsWithColors = Arrays.asList(
                new ObjectWithColors(Arrays.asList("red", "blue", "green")),
                new ObjectWithColors(Arrays.asList("yellow", "blue", "purple")),
                new ObjectWithColors(Arrays.asList("red", "green", "pink"))
        );
        long numberOfUniqueColors = objectsWithColors.stream()
                .flatMap(object -> object.getColors().stream())
                .distinct()
                .count();
        System.out.println("number of unique colors"+ numberOfUniqueColors);


        // Создаем несколько продуктов с тегами
        Product product1 = new Product("Laptop", Arrays.asList("electronics", "computers"));
        Product product2 = new Product("Headphones", Arrays.asList("electronics", "audio"));
        Product product3 = new Product("Book", Arrays.asList("literature"));
        Product product4 = new Product("Smartphone", Arrays.asList("electronics", "phones"));

        // Создаем несколько заказов с продуктами
        Order order1 = new Order(Arrays.asList(product1, product2, product3));
        Order order2 = new Order(Arrays.asList(product2, product4));
        Order order3 = new Order(Arrays.asList(product3, product4, product1));

        // Создаем список заказов
        List<Order> orders = Arrays.asList(order1, order2, order3);

        // Код для обработки списка заказов
        long numberOfUniqueTags = orders.stream()
                .flatMap(product -> product.getProducts().stream())
                .flatMap(tag -> tag.getTags().stream())
                .distinct()
                .count();
        System.out.println("Number of unique tags = " + numberOfUniqueTags);


        //у вас есть список строк, в каждой из которых есть числа. Используя flatMap, sorted и другие необходимые методы,
        // отсортируйте числа в каждой строке в порядке убывания и выведите результат.
        List<String> numbersAsString = Arrays.asList("5, 3, 8, 1", "4, 7, 2, 9", "6, 10, 2, 1");

        List<String> sortedNumbers = numbersAsString.stream()
                // Шаг 1: Преобразование каждой строки в список чисел
                .map(numbers -> Arrays.stream(numbers.split(", "))
                        .map(Integer::parseInt)
                        .toList())
                // Шаг 2: Сортировка каждого списка по убыванию
                .map(numbers -> numbers.stream()
                        .sorted(Comparator.reverseOrder())
                        .toList())
                // Шаг 3: Преобразование каждого списка чисел обратно в строки
                .map(numbers -> numbers.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")))
                // Шаг 4: Объединение всех строк в один список
                .toList();

        // Вывод результата
        sortedNumbers.forEach(System.out::println);


        List<String> rawWords = Arrays.asList("apple", "orange", "banana", "grape", "melon", "kiwi", "pear");
        List<String> endFiltList = rawWords.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .toList();
        System.out.println("endFiltList = " + endFiltList);


        List<String> countryName = Arrays.asList( "France", "Germany", "Italy", "Canada", "United States", "Australia");
        List<String> filtCountry = countryName.stream()
                .sorted(Comparator.naturalOrder())
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        System.out.println("filtCountry = " + filtCountry);

        // Добавление отладочного вывода с использованием peek():
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape");

        List<String> filteredFruits = fruits.stream()
                .filter(fruit -> fruit.length() > 5)
                .peek(filtFrut-> System.out.println("Filtered Fruits " + filtFrut))
                .map(String::toUpperCase)
                .peek(upperedCase -> System.out.println("Upper Case "+ upperedCase))
                .toList();
        System.out.println("filteredFruits = " + filteredFruits);

        //У вас есть список целых чисел. Используйте distinct(), чтобы получить список уникальных чисел.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 1);
        List<Integer> filteredNumbers = numbers.stream()
                .distinct()
                .toList();
        System.out.println("filteredNumbers " + filteredNumbers);

        //У вас есть список строк с повторяющимися значениями. Используйте distinct(), чтобы получить список уникальных строк.
        List<String> strings = Arrays.asList("apple", "banana", "orange", "banana", "grape");
        List<String> distinctStrings = strings.stream()
                .distinct()
                .toList();
        System.out.println("distinctStrings " + distinctStrings);

        //У вас есть список объектов, каждый из которых содержит поле с именем.
        // Используйте distinct(), чтобы получить список уникальных имен.
        List<Person> people = Arrays.asList(
                new Person("John"),
                new Person("Alice"),
                new Person("John"),
                new Person("Bob")
        );
        List<Person> uniquePerson = people.stream()
                .distinct()
                .toList();
        uniquePerson.forEach(person -> System.out.println(person.getName()));

        //У вас есть список строк с числами и буквами. Отфильтруйте только те строки,
        // которые содержат только цифры. Затем преобразуйте каждую строку в число и найдите сумму этих чисел.
        List<String> mixedStrings = Arrays.asList("123", "abc", "45", "789", "xyz");
        Optional<Integer> filteredStrings = mixedStrings.stream()
                .filter(mixString -> mixString.matches("\\d+"))
                .map(Integer::parseInt)
                .reduce(Integer::sum);
        filteredStrings.ifPresent(value -> System.out.println("value " + value));

        //У вас есть список заказов, каждый из которых содержит список продуктов.
        // Найдите общее количество уникальных тегов для всех продуктов во всех заказах.
        // Выведите на печать список этих тегов в алфавитном порядке.
        /*OtherOrder order1a = new Order(Arrays.asList("apple", "banana", "orange"));
        Order order2a = new Order(Arrays.asList("banana", "grape"));
        Order order3a = new Order(Arrays.asList("apple", "kiwi", "pear"));

        List<Order> allOrders = Arrays.asList(order1, order2, order3);*/


    }
}

class Person{
    private String name;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}


class ObjectWithColors {
    private List<String> colors;

    public ObjectWithColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getColors() {
        return colors;
    }
}

class Product{
    private String name;
    private List<String> tags;

    public Product(String name, List<String> tags) {
        this.name = name;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

class Order{
    private List<Product> products;

    public Order(List<Product> products){
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}

class OtherOrder{
    private List<String> products;
    public OtherOrder(List<String> products){
        this.products = products;
    }
    public List<String> getProducts(){
        return this.products;
    }
}