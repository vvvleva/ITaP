import java.util.concurrent.ConcurrentHashMap;

public class Sales{
    public static void main(String[] args) {
    Sales salesTracker = new Sales();
    salesTracker.addSale("Яблоки", 5);
    salesTracker.addSale("Груши", 3);
    salesTracker.addSale("Вишня", 2);
    salesTracker.addSale("Яблоки", 4);

    salesTracker.printSales();
    System.out.println("Общая сумма продаж: " + salesTracker.getTotalSales());
    System.out.println("Наиболее популярный товар: " + salesTracker.getMostPopularItem());
}
    private ConcurrentHashMap<String, Integer> salesMap; // Создаем приватное поле salesMap типа ConcurrentHashMap для хранения продаж

    public Sales() {   // Конструктор класса Sales, инициализирующий salesMap
        this.salesMap = new ConcurrentHashMap<>();
    }

    public void addSale(String item, int quantity) {  // Метод для добавления продажи товара с указанным количеством
        salesMap.put(item, salesMap.getOrDefault(item, 0) + quantity); // Добавляем или обновляем количество продаж товара в salesMap
    }

    public void printSales() {  // Метод для вывода списка проданных товаров
        System.out.println("Список проданных товаров:");
        for (String item : salesMap.keySet()) {
            System.out.println(item + ": " + salesMap.get(item));
        }
    }

    public double getTotalSales() { // Метод для подсчета общей суммы продаж
        double totalSales = 0;
        for (int sales : salesMap.values()) {
            totalSales += sales;
        }
        return totalSales;
    }

    public String getMostPopularItem() { // Метод для нахождения наиболее популярного товара (с наибольшим количеством продаж)
        int maxSales = 0;
        String mostPopularItem = null;
        for (String item : salesMap.keySet()) {
            if (salesMap.get(item) > maxSales) {
                maxSales = salesMap.get(item);
                mostPopularItem = item;
            }
        }
        return mostPopularItem;
    }
}
