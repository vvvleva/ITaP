import java.util.*;
import java.util.stream.*;

public class DataProcessors {

    @DataProcessor(comment = "Удаляем из входного списка пустые строки или строки из пробелов")
    public static List<String> filterData(List<String> data) {
        return data.stream() // Вызов метода stream(), который создает поток элементов из списка data
                .filter(line -> !line.trim().isEmpty()) // Фильтрация строк: оставляем только непустые строки
                .collect(Collectors.toList()); // Сборка элементов в список
    }

    @DataProcessor(comment = "Преобразуем все строки из входного списка в верхний регистр")
    public static List<String> transformData(List<String> data) {
        return data.stream()
                .map(String::toUpperCase) // Преобразование каждой строки в верхний регистр
                .collect(Collectors.toList()); // Сборка элементов в список
    }

    @DataProcessor(comment = "// объединяем все строки во входном списке в одну строку разделённую запятыми и пробелами")
    public static List<String> aggregateData(List<String> data) {
        String aggregated = data.stream().collect(Collectors.joining(", ")); // Объединение всех строк в одну с разделителем ", "
        return List.of(aggregated); // Возврат результата в виде списка с одним элементом
    }
}
