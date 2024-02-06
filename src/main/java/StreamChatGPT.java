import java.util.Arrays;
import java.util.List;


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
    }
}
