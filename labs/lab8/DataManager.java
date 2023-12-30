import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.*;
import java.lang.reflect.*;

public class DataManager {
    private List<String> data = new ArrayList<>(); // Приватный список строк data
    private List<Method> processors = new ArrayList<>(); //  Приватный список методов processors

    public void registerDataProcessor(Object processor) {
        for (Method method : processor.getClass().getDeclaredMethods()) { // Перебор всех методов в объекте processor
            if (method.isAnnotationPresent(DataProcessor.class)) { // Проверка, присутствует ли у метода аннотация DataProcessor
                processors.add(method); // Добавление метода в список processors
            }
        }
    }

    public void loadData(String source) throws IOException {
        data = Files.readAllLines(Paths.get(source)); // Загрузка данных из файла по указанному пути в список data
    }

    public void processData() {
        ExecutorService executor = Executors.newFixedThreadPool(processors.size()); // Создание исполнителя с фиксированным количеством потоков

        List<Future<String>> futures = processors.stream() // Преобразование списка processors в поток и выполнение операций над каждым элементом
                .map(method -> executor.submit(() -> { // Преобразование каждого метода в результат Future
                    try {
                        Object result = method.invoke(null, data); // Вызов метода с передачей списка data в качестве аргумента
                        if (result instanceof List<?>) { // Проверка, является ли результат типом List
                            List<?> resultList = (List<?>) result; // Приведение результата к типу List
                            if (resultList.stream().allMatch(item -> item instanceof String)) { // Проверка, содержатся ли все элементы в списке resultList типа String
                                List<String> processedData = resultList.stream() // Преобразование каждого элемента в строковое представление
                                        .map(String::valueOf) // Ссылка на метод, используется для преобразования из String в URI
                                        .collect(Collectors.toList()); // Сборка элементов в список
                                String comment = method.getAnnotation(DataProcessor.class).comment(); // Получение комментария из аннотации DataProcessor
                                return comment + "\\n" + processedData.toString() + "\\n"; // Возврат комментария и обработанных данных в виде строки
                            } else {
                                return "Error: Список содержит элементы, которые не являются строковыми.\\n"; // Возврат сообщения об ошибке
                            }
                        } else {
                            return "Error: Метод не вернул List<String>.\\n"; // Возврат сообщения об ошибке
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) { // Обработка исключений при вызове метода
                        e.printStackTrace(); // Вывод стека вызовов исключения
                        return ""; // Возврат пустой строки в случае ошибки
                    }
                }))
                .collect(Collectors.toList()); // Сбор результатов в список futures

        data = futures.stream() // Преобразование списка futures в поток и выполнение операций над каждым элементом
                .flatMap(future -> {
                    try {
                        return Stream.of(future.get()); // Получение результата из Future и преобразование его в поток
                    } catch (InterruptedException | ExecutionException e) { // Обработка исключений при получении результата из Future
                        e.printStackTrace(); // Вывод стека вызовов исключения
                    }
                    return Stream.empty(); // Возврат пустого потока в случае ошибки
                })
                .collect(Collectors.toList()); // Сбор элементов потока в список

        executor.shutdown(); // Остановка исполнителя
    }

    public void saveData(String destination) throws IOException {
        Files.write(Paths.get(destination), data); // Запись данных из списка data в файл по указанному пути
    }
}
