import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MaxElementFinder {
    private static int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; // Создаем матрицу
    private static int numThreads = 3; // Указываем количество потоков
    private static int maxElement = Integer.MIN_VALUE; // Наибольший элемент

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads); // Создаем исполнителя с фиксированным пулом потоков

        int rowsPerThread = matrix.length / numThreads; // Вычисляем количество строк на каждый поток

        for (int i = 0; i < numThreads; i++) {
            int start = i * rowsPerThread; // Начальная строка для обработки
            int end = (i == numThreads - 1) ? matrix.length : (i + 1) * rowsPerThread; // Конечная строка для обработки
            executor.execute(new MaxElementTask(start, end)); // Выполняем задачу поиска наибольшего элемента для каждой части матрицы
        }

        executor.shutdown(); // Останавливаем прием новых задач и завершаем работу всех потоков после завершения текущих задач

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Ждем завершения работы всех потоков
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Max element: " + maxElement); // Выводим наибольший элемент
    }

    static class MaxElementTask implements Runnable { // Вложенный класс для выполнения задачи поиска наибольшего элемента в отдельном потоке

        private int start; // Начальный индекс для поиска наибольшего элемента в матрице
        private int end; // Конечный индекс для поиска наибольшего элемента в матрице

        public MaxElementTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int localMax = Integer.MIN_VALUE; // Инициализируем локальный максимальный элемент как минимальное значение типа int
            for (int i = start; i < end; i++) { // Перебираем строки матрицы в заданном диапазоне
                for (int j = 0; j < matrix[i].length; j++) { // Перебираем элементы в строке матрицы
                    if (matrix[i][j] > localMax) {
                        localMax = matrix[i][j]; // Находим локальный наибольший элемент в части матрицы
                    }
                }
            }
            synchronized (MaxElementFinder.class) { // Синхронизируем доступ к наибольшему элементу
                if (localMax > maxElement) {
                    maxElement = localMax; // Обновляем наибольший элемент, если нашли больший в локальной части
                }
            }
        }
    }
}
