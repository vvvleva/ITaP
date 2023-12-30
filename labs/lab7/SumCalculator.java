import java.util.concurrent.ExecutorService; // Импортируем класс ExecutorService для создания и управления потоками
import java.util.concurrent.Executors; // Импортируем класс Executors для создания исполнителя (executor)
import java.util.concurrent.TimeUnit; // Импортируем класс TimeUnit для работы с единицами времени

public class SumCalculator {
    private static int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Создаем массив целых чисел
    private static int numThreads = 4; // Указываем количество потоков
    private static int totalSum = 0; // Общая сумма

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads); // Создаем исполнителя с фиксированным пулом потоков

        int chunkSize = array.length / numThreads; // Вычисляем размер "куска" массива для каждого потока

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize; // Начальный индекс "куска" массива
            int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize; // Конечный индекс "куска" массива
            executor.execute(new SumTask(start, end)); // Выполняем задачу суммирования для каждого "куска" массива
        }

        executor.shutdown(); // Останавливаем прием новых задач и завершаем работу всех потоков после завершения текущих задач

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Ждем завершения работы всех потоков
        } catch (InterruptedException e) { // Исключение, которое возникает, если поток ожидает, спит или блокируется для какого-либо события, и это событие прерывается другим потоком
            e.printStackTrace();
        }
        System.out.println("Total sum: " + totalSum); // Выводим общую сумму
    }

    static class SumTask implements Runnable { // Вложенный класс для выполнения задачи суммирования в отдельном потоке
        private int start;
        private int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int localSum = 0;
            for (int i = start; i < end; i++) {
                localSum += array[i]; // Вычисляем локальную сумму для "куска" массива
            }
            synchronized (SumCalculator.class) { // Синхронизируем доступ к общей сумме
                totalSum += localSum; // Добавляем локальную сумму к общей сумме
            }
        }
    }
}
