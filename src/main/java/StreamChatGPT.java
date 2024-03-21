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

        // Создаем несколько продуктов с тегами
        ProductTwo productTwo1 = new ProductTwo("Laptop", Arrays.asList("electronics", "computers"));
        ProductTwo productTwo2 = new ProductTwo("Headphones", Arrays.asList("electronics", "audio"));
        ProductTwo productTwo3 = new ProductTwo("Book", Arrays.asList("literature"));
        ProductTwo productTwo4 = new ProductTwo("Smartphone", Arrays.asList("electronics", "phones"));

        // Создаем несколько заказов с продуктами
        OrderTwo orderTwo1 = new OrderTwo(Arrays.asList(productTwo1, productTwo2, productTwo3));
        OrderTwo orderTwo2 = new OrderTwo(Arrays.asList(productTwo2, productTwo4));
        OrderTwo orderTwo3 = new OrderTwo(Arrays.asList(productTwo3, productTwo4, productTwo1));

        List<OrderTwo> allOrdersTwo = Arrays.asList(orderTwo1, orderTwo2, orderTwo3);

        List<String> uniqueTags = allOrdersTwo.stream()
                .flatMap(allOrderTwo -> allOrderTwo.getProducts().stream())
                .flatMap(product -> product.getTags().stream())
                .distinct()
                .sorted()
                .toList();
        System.out.print(String.join(", ", uniqueTags));
        System.out.println("");
        System.out.println("********************");

        //У вас есть список людей с именами и возрастом.
        // Отфильтруйте людей старше 25 лет, затем найдите средний возраст этих людей.
        // Выведите на печать имена людей, старше 25 лет, в алфавитном порядке.

        //Список людей
        List<PersonThree> peopleThree = Arrays.asList(
                new PersonThree("Alice", 28),
                new PersonThree("Bob", 22),
                new PersonThree("Charlie", 30),
                new PersonThree("David", 25)
        );
        List<String> selectList = peopleThree.stream()
                .filter(personThree -> personThree.getAge() > 25)
                .map(personThree -> personThree.getName())
                .toList();
        System.out.println(String.join(", ",selectList));

        double averageAgePeoples = peopleThree.stream()
                .filter(personThree -> personThree.getAge() > 25)
                .mapToDouble(PersonThree :: getAge)
                .average()
                .orElse(0.0);
        System.out.println("AVG age = "+averageAgePeoples);

        //У вас есть список строк.
        // Выведите каждую строку в верхнем регистре с использованием peek(), после чего соберите результат в список.
        List<String> wordsList = Arrays.asList("apple", "banana", "orange", "grape");
        List<String> sortList = wordsList.stream()
                .map(String::toUpperCase)
                .peek(word -> System.out.println("UpperCase "+word))
                .map(String::toLowerCase)
                .toList();
        System.out.println("result "+sortList);

        //У вас есть список чисел. Отфильтруйте только положительные числа,
        // и для каждого положительного числа выведите его значение в квадрате с использованием peek().
        // Затем соберите результат в список.
        List<Integer> anyNumbers = Arrays.asList(-2, 5, -8, 10, 3, -6);
        List<Double> sortNumbers = anyNumbers.stream()
                .filter(number -> number > 0)
                .map(num -> Math.pow(num,2))
                .peek(num -> System.out.println("POW result "+num))
                .toList();
        System.out.println("End result "+ sortNumbers);

        //У вас есть список людей. Выведите имена всех людей, чьи имена начинаются с буквы "A",
        // с использованием peek(). Затем соберите результат в список.
        List<PersonThree> peoplePeek = Arrays.asList(
                new PersonThree("Alice", 28),
                new PersonThree("Bob", 22),
                new PersonThree("Charlie", 30),
                new PersonThree("David", 25)
        );
        List<String> endPeek = peoplePeek.stream()
                .filter(person -> person.getName().startsWith("A"))
                .peek(pers -> System.out.println("Name "+pers.getName()))
                .map(PersonThree::getName)
                .toList();
        System.out.println("Name start with A "+endPeek);

        //У вас есть список слов. Найдите первое слово, которое содержит букву "a" и имеет длину больше 3.
        List<String> wordsForSearch = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");
        Optional<String> foundWords = wordsForSearch.stream()
                .filter(word -> word.contains("a") && word.length() > 3)
                .findFirst();
        foundWords.ifPresent(System.out::println);

        //У вас есть список чисел. Найдите первое положительное четное число.
        List<Integer> numbersForSearch = Arrays.asList(-2, 5, -8, 10, 3, -6);
        Optional<Integer> foundNumber = numbersForSearch.stream()
                .filter(num -> num > 0 && num % 2 == 0)
                .findFirst();
        foundNumber.ifPresent(System.out::println);

        //У вас есть список людей.
        // Найдите первого человека, чье имя начинается с буквы "B" и возраст больше 25.
        List<PersonThree> peopleForFirst = Arrays.asList(
                new PersonThree("Alice", 28),
                new PersonThree("Bob", 47),
                new PersonThree("Charlie", 30),
                new PersonThree("David", 25)
        );
        Optional<PersonThree> foundFirst = peopleForFirst.stream()
                .filter(found -> found.getName().startsWith("B") && found.getAge() > 25)
                .findFirst();
        foundFirst.ifPresent(men -> System.out.println("Name: "+men.getName()+" Age: "+men.getAge()));

        //У вас есть список списков целых чисел. Используйте flatMap(), чтобы получить один поток целых чисел.
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        List<Integer> resList = listOfLists.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("List of numbers" + resList);

        //У вас есть список строк, представляющих слова в предложениях.
        // Используйте flatMap(), чтобы получить список слов из всех предложений.
        List<String> sentences = Arrays.asList(
                "Hello world",
                "How are you",
                "Java is awesome"
        );
        List<String> allWords = sentences.stream()
                .map(sentence->sentence.split("\\s+"))
                .flatMap(Arrays::stream)
                .toList();
        System.out.println("List of words" + allWords);

        //У вас есть список заказов, каждый из которых содержит список продуктов.
        // Используйте flatMap(), чтобы получить список всех продуктов из всех заказов.
        List<OrderThree> ordersThree = Arrays.asList(
                new OrderThree(Arrays.asList(new ProductThree("Laptop"), new ProductThree("Phone"))),
                new OrderThree(Arrays.asList(new ProductThree("Headphones"), new ProductThree("Tablet"))),
                new OrderThree(Arrays.asList(new ProductThree("Mouse"), new ProductThree("Keyboard")))
        );
        List<ProductThree> listOfOrders = ordersThree.stream()
                .flatMap(order->order.getProductThrees().stream())
                .toList();
        listOfOrders.forEach(listOrder -> System.out.println(listOrder.getName()));

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

class PersonThree{
    private String name;
    private int age;

    public PersonThree(String name, int age){
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

class ProductTwo {
    private String name;
    private List<String> tags;

    public ProductTwo(String name, List<String> tags) {
        this.name = name;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", tags=" + tags +
                '}';
    }
}

class OrderTwo {
    private List<ProductTwo> products;

    public OrderTwo (List<ProductTwo> products) {
        this.products = products;
    }

    public List<ProductTwo> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                '}';
    }
}

class ProductThree{
    private String name;

    public ProductThree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class OrderThree{
    private List<ProductThree> productThrees;

    public OrderThree(List<ProductThree> productThree){
        this.productThrees = productThree;
    }

    public List<ProductThree> getProductThrees() {
        return productThrees;
    }
}