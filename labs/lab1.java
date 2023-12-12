abstract class Institution1 {   // Создаем абстрактный класс, который содержит приватные поля
    private String name;
    private String assortiment;
    private int capacity;
    private int visitors;
    private static int count = 0; // Статическая переменная для подсчёта созданных объектов

   // Прописываем ссылки на экземпляр класса
    public Institution1(String name, String assortiment, int capacity, int visitors) {
        this.name = name;
        this.assortiment = assortiment;
        this.capacity = capacity;
        this.visitors = 13;
        count++;
    }

    // Статический метод для получения количества созданных объектов
    public static int getCount() {
        count++;
         return count;
            }

    // Абстрактный метод, который должен быть реализован в дочерних классах
    public abstract void displayInfo();

    //get — получать (метод для получения значения поля) и set — устанавливать (т.е. “метод для установки значения поля”)
    public String getName() {
        return name;
    }

    public String getAssortiment() {
        return assortiment;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getVisitors() {
        return visitors;
    }

    public int setVisitors(int visitors) {
        this.visitors = visitors;
        return visitors;
    }

    // Метод для увеличения числа посетителей
    public void increaseVisitors(int count) {
        this.visitors += count;
    }
}

// Дочерние классы, наследуемые от класса Institution1
    public class Cafe extends Institution1 {
        private int freeTables; // Дополнительное поле

    // Конструктор с параметрами
    public Cafe(String name, String assortiment, int capacity, int freeTables) {
        super(name, assortiment, capacity,freeTables);
        this.freeTables = freeTables;
    }

    // Переопределение метода displayInfo
    @Override
    public void displayInfo() { // применение овертайма. Есть одинаковое имя, но имеется отличие в типах и кол-ве параметров
        System.out.println("Cafe name: " + getName());
        System.out.println("Available in stock: " + getAssortiment());
        System.out.println("Capacity: " + getCapacity() + " people");
        System.out.println("Free tables: " + freeTables);
        System.out.println("Visitors: " + getVisitors());
    }
    //Доп метод
    public void boxOffice(int boxOffice){   // Метод для вывода информации о кол-ве открытых касс
        System.out.println("Now open " + boxOffice + " free cash registers");
    }
}

    class Shop extends Institution1{
        private int discountedProducts;  // Дополнительное поле

 // Конструктор с параметрами
        public Shop(String name, String assortiment, int capacity, int discountedProducts){
            super(name, assortiment, capacity, discountedProducts);
            this.discountedProducts = discountedProducts;
        }

// Переопределение метода displayInfo
    @Override
    public void displayInfo(){  // применение оверзайда
        System.out.println("Shop name: " + getName());
        System.out.println("Available in stock: " + getAssortiment());
        System.out.println("Capacity: " + getCapacity() + " people");
        System.out.println("Free tables: " + discountedProducts);
        System.out.println("Visitors: " + getVisitors());
        }

        //Доп метод
        public void boxOffice(int boxOffice){   // Метод для вывода информации о кол-ве открытых касс
            System.out.println("Now open " + boxOffice + " free cash registers");
        }
}
     class Librarry extends Institution1 {
    private int bookCount;  // Дополнительное поле

    // Конструктор с параметрами
    public Librarry(String name, String assortiment, int capacity, int bookCount) {
        super(name, assortiment, capacity, bookCount);
        this.bookCount = bookCount;
    }

    // Переопределение метода displayInfo
    @Override
    public void displayInfo() { // применение оверрайда
        System.out.println("Library Name: " + getName());
        System.out.println("Available in stock: " + getAssortiment());
        System.out.println("Capacity: " + getCapacity() + " people");
        System.out.println("Number of Books: " + bookCount);
        System.out.println("Visitors: " + getVisitors());
    }

    // Перегрузка boxOffice(), имеет различные параметры, но выполняет одну и ту же функцию - вывод информации о кол-ве открытых касс.
    public void boxOffice(int boxOffice) {   // Метод для вывода информации о кол-ве открытых касс
        System.out.println("Now open " + boxOffice + " racks for receiving books");
    }
}

// Создаем объекты классов Cafee, Shop, Library и задаем значения полей
    class Main {
        public static void main(String[] args) {
            Cafe cafee = new Cafe("Cafe", "coffee", 17,  2);  // Создаем объект класса Cafee
            Shop shop = new Shop("Shop", "winter clothes", 150, 57);   // Создаем объект класса Shop
            Librarry library = new Librarry("Library", "books", 290, 9000);   // Создаем объект класса Library

            cafee.displayInfo();   // Вызываем метод displayInf0()
            cafee.boxOffice(1);   // Вызываем дополнительный метод currency()

            shop.displayInfo();   // Вызываем метод displayInfo()
            shop.boxOffice(5);   // Вызываем дополнительный метод boxOffice()

            library.displayInfo();   // Вызываем метод displayInfo()
            library.boxOffice(2);    // Вызываем дополнительный метод boxOffice()

            System.out.println("Total number of objects created: " + Institution1.getCount());    // Выводим количество созданных объектов
        }
    }
