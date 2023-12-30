import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> inputData = List.of("Happy new year!!!", "01.01.2024", "", " ", "Merry Christmas!"); // Создание списка строк inputData
        java.nio.file.Files.write(java.nio.file.Paths.get("C:\\Users\\shkil\\OneDrive\\Рабочий стол\\Учёба\\3 семестр\\Программирование\\Лабы\\lab8\\input.txt"), inputData); // Запись данных из списка inputData в файл "input.txt"
        DataManager dataManager = new DataManager();  // Создание объекта dataManager
        dataManager.registerDataProcessor(new DataProcessors()); // Регистрация обработчика данных DataProcessors в dataManager
        dataManager.loadData("C:\\Users\\shkil\\OneDrive\\Рабочий стол\\Учёба\\3 семестр\\Программирование\\Лабы\\lab8\\input.txt"); // Загрузка данных из файла "input.txt" в dataManager
        dataManager.processData();  // Обработка данных
        dataManager.saveData("C:\\Users\\shkil\\OneDrive\\Рабочий стол\\Учёба\\3 семестр\\Программирование\\Лабы\\lab8\\output.txt"); // Сохранение данных в файл "output.txt"
    }
}
