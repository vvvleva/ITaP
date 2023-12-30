import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WarehouseTransfer {
    private static int[] weights = {20, 30, 40, 50, 60, 70, 80, 90}; // Веса товаров
    private static int numWorkers = 3; // Количество грузчиков
    private static int maxWeight = 150; // Максимальный вес для переноски
    private static CountDownLatch startSignal = new CountDownLatch(1); // Сигнал для начала переноски
    private static CountDownLatch endSignal = new CountDownLatch(numWorkers); // Сигнал для завершения переноски

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(numWorkers); // Создаем исполнителя с фиксированным пулом потоков

        for (int i = 0; i < numWorkers; i++) {
            executor.execute(new Worker(i)); // Выполняем задачу для каждого грузчика
        }

        startSignal.countDown(); // Отправляем сигнал для начала переноски

        try {
            endSignal.await(); // Ждем завершения переноски товаров
        } catch (InterruptedException e) { // Исключение, которое возникает, если поток ожидает, спит или блокируется для какого-либо события, и это событие прерывается другим потоком
            e.printStackTrace();
        }

        System.out.println("All items have been transferred to the new warehouse");
        executor.shutdown(); // Останавливаем прием новых задач и завершаем работу всех потоков после завершения текущих задач
    }

    static class Worker implements Runnable {
        private int workerId; // Идентификация работника

        public Worker(int workerId) { // Конструктор класса Worker, принимающий идентификатор работника
            this.workerId = workerId;
        }

        @Override
        public void run() {
            try {
                startSignal.await(); // Ждем сигнала для начала переноски
                int currentWeight = 0; // Инициализация переменной для отслеживания текущего веса
                for (int i = workerId; i < weights.length; i += numWorkers) {
                    if (currentWeight + weights[i] <= maxWeight) {
                        currentWeight += weights[i]; // Переносим товар, если не превышен максимальный вес
                        System.out.println("Worker " + workerId + " transferred item with weight " + weights[i]);
                    } else {
                        break; // Прекращаем переноску, если превышен максимальный вес
                    }
                }
                endSignal.countDown(); // Отправляем сигнал о завершении переноски, уменьшая счетчик
            } catch (InterruptedException e) { // Обработка исключения при прерывании потока
                e.printStackTrace();
            }
        }
    }
}
