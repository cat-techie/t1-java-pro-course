package ru.t1.pmorozov;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Иван1", 35, Position.MANAGER),
            new Employee("Иван2", 44, Position.DIRECTOR),
            new Employee("Иван3", 54, Position.ENGINEER),
            new Employee("Иван4", 69, Position.ENGINEER),
            new Employee("Иван5", 26, Position.ENGINEER),
            new Employee("Иван6", 33, Position.MANAGER),
            new Employee("Иван7", 42, Position.MANAGER),
            new Employee("Иван8", 23, Position.ENGINEER),
            new Employee("Иван9", 44, Position.MANAGER),
            new Employee("Иван10", 39, Position.ENGINEER)
    ));

    static ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 8, 12, 5, 1, 13, 2, 7, 1, 5, 10, 10, 13, 4, 26, 8, 12));

    static ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(
            "раздражительность",
            "ритмичный",
            "романтичный",
            "скрытный",
            "словесный",
            "сопротивляемость",
            "соседский",
            "сравнимый",
            "сценичный",
            "техничный"
    ));

    static String[] wordsArray = new String[]{
            "апельсин груша лимон яблоко слива",
            "компьютер телефон телевизор принтер ноутбук",
            "футболка шорты жилет кроссовки свитер",
            "книга читатель библиотека писатель страница"
    };

    static String words = "кот собака мышь крыса белка заяц лев тигр слон жираф белка кот кот кот крыса тигр крыса тигр лев жираф";

    public static void main(String[] args) {

        // Реализуйте удаление из листа всех дубликатов
        // v1
        var resultListUsingStream = list.stream().distinct().toList();
        System.out.println(resultListUsingStream);
        // v2
        var resultListUsingSet = new HashSet<>(list);
        System.out.println(resultListUsingSet);

        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        var thirdLargeNum = list.stream().sorted(Integer::compare).toList().get(list.size() - 3);
        System.out.println(thirdLargeNum);

        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
        var thirdLargeUniqNum = list.stream().distinct().sorted(Comparator.reverseOrder()).toList().get(2);
        System.out.println(thirdLargeUniqNum);

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста

        var threeOldestEngineers = employees.stream()
                .filter(e -> e.getPosition() == Position.ENGINEER)
                .sorted((e1, e2) -> Integer.compare(e2.getAge(), e1.getAge()))
                .toList()
                .subList(0, 3);
        threeOldestEngineers.stream().map(Employee::getName).forEach(System.out::println);

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
        var avgAgeEngineers = employees.stream()
                .filter(e -> e.getPosition() == Position.ENGINEER)
                .map(Employee::getAge)
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
        System.out.println(avgAgeEngineers);

        // Найдите в списке слов самое длинное
        var longestWord = wordsList.stream().max(Comparator.comparing(String::length)).get();
        System.out.println(longestWord);

        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        var animalMap = Arrays.stream(words.split(" "))
                .distinct()
                .collect(Collectors.toMap(w -> w, v -> words.split(v, -1).length - 1));
        System.out.println(animalMap);

        // Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        wordsList.stream().sorted().toList().stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);

        // Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        var longestWord2 = Arrays.stream(wordsArray).flatMap(w -> Arrays.stream(w.split(" "))).max(Comparator.comparing(String::length)).get();
        System.out.println(longestWord2);
    }
}