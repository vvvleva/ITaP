public class Array {
    public static void main(String[] args) {
        // Создаем массив
        int[] arr = {1, 2, 3, 4, 5, 5};

        int sum = 0;
        int count = 0;

        try {  //Обработка массива
            for (int i = 0; i < arr.length; i++) {
                // Проверяем, является ли элемент числом
                if (isNumber(arr[i])) {
                    sum += arr[i];
                    count++;
                } else {
                    throw new NumberFormatException(); // генерация нового исключения
                }
            }

            // Вычисляем среднее арифметическое
            double average = (double) sum / count; // для работы с дробными числами используем тип double
            System.out.println("Среднее арифметическое элементов массива: " + average);
        } catch (ArrayIndexOutOfBoundsException e) {  // Обработка исключения типа ExceptionType1
            System.out.println("Выход за границы массива");
        } catch (NumberFormatException e) {  // Обработка исключения типа ExceptionType2
            System.out.println("Элемент массива не является числом");
        } // "e" в команде используется как обозначение переменной типа Exception. При возникновении исключения типа NumberFormatException, исключение будет присвоено
        // переменной "e". Затем, используя данную переменную, можно обработать исключение или выполнить необходимые действия при его возникновении
    }

    // Метод для проверки, является ли значение целым числом
    private static boolean isNumber(int value) {
        try {
            Integer.parseInt(String.valueOf(value)); // преобразуем значение в строку перед его передачей в метод
            return true; // Если значение может быть преобразовано в целое число, то метод  не генерирует исключение и возвращает true
        } catch (
                NumberFormatException e) { //если значение  не может быть преобразовано в целое число, то метод  генерирует исключение
            return false;
        }
    }
}
