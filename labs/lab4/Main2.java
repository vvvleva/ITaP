public class Main2 {
    // Главный метод программы
    public static void main(String[] args) {
        String input = "abc"; // Строка, которую мы попытаемся преобразовать в число

        try {
            int number = Integer.parseInt(input); // Попытка преобразовать строку в число
            System.out.println("Число: " + number); // Выводим число, если преобразование прошло успешно
        } catch (NumberFormatException e) { // Обрабатываем исключение NumberFormatException
            throw new CustomNumberFormatException("Строка не является числом"); // Выводим исключение CustomNumberFormatException
        }
    }
}
