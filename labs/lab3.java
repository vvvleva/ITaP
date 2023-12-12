import java.util.ArrayList;
import java.util.List;

public class Main{
    public static class Employee { // Класс, представляющий сотрудника
        private int id; // Создание поля ID сотрудника
        private String name; // Создание поля имя сотрудника
        private String position; //Создание поля должность сотрудника
        private int salary; //Создание поля заработная плата

        // Конструктор с параметрами
        public Employee(int id, String name, String position, int salary) { // Конструктор класса Employee, который принимает на вход значения для полей
            this.id = id; // ссылка на экземпляр класса
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        // Геттеры для доступа к полям класса (получение значения поля)
        public int getId() {
            return id;
        } // получает id сотрудника
        public String getName() {
            return name;
        }
        public String getPosition() {
            return position;
        }
        public int getSalary() {
            return salary;
        }

        @Override
        public String toString() { //Метод возвращения строкового представления объекта класса Employee
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", position='" + position + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    private static final int TABLE_SIZE = 10; // Объявление константы TABLE_SIZE

    private List<Employee>[] hashTable; // Объявление массива списков ArrayList для хранения элементов хеш-таблицы

    public Main() {
        hashTable = new ArrayList[TABLE_SIZE]; // создание массива списков для хранения элементов хеш-таблицы
        for (int i = 0; i < TABLE_SIZE; i++) { //проходим по каждому элементу, если он меньше константы, добавляем в список
            hashTable[i] = new ArrayList<>(); //инициализация каждого элемента массива как пустого списка
        }
    }

    private int hash(int id) { // Метод hash()- вычисляет хеш-код для заданного id с помощью деления по модулю
        return id % TABLE_SIZE;
    } // испоьзуем деление по модулю

    public void insert(Employee employee) {  // Метод insert() - добавляет сотрудника в хеш-таблицу
        int hash = hash(employee.getId()); // вычисление хеш-кода для заданного id с помощью метода hash()
        hashTable[hash].add(employee);  // добавление сотрудника в соответствующий спискок в хеш-таблице
    }

    public Employee search(int id) { // метод для поиска сотрудника по id
        int hash = hash(id); // вычисление хеш-кода для заданного id с помощью метода hash()
        for (Employee employee : hashTable[hash]) { // перебор сотрудников в соответствующем списке в хеш-таблице
            if (employee.getId() == id) { // сравнение id с заданным id для поиска сотрудника
                return employee;
            }
        }
        return null;
    }

    public void  remove(int id) { //метод для удаления сотрудника из хеш-таблицы по id
        int hash = hash(id); // вычисление хеш-кода для заданного id с помощью метода hash()
        for (Employee employee : hashTable[hash]) { // перебор сотрудников в соответствующем списке в хеш-таблице
            if (employee.getId() == id) { // когда находим нужного сотрудника
                hashTable[hash].remove(employee); // удаляем с помощью метода remove()
                return;
            }
        }
    }

    // Пример использования
    public static void main(String[] args) {
        Main hashTable = new Main();

        //Создадим 3 сотрудников с разными id, фамилиями, должностями и зарплатами
        Employee employee1 = new Employee(1, "Иванов", "Дизайнер", 60000);
        Employee employee2 = new Employee(2, "Петров", "Разработчик", 70000);
        Employee employee3 = new Employee(3, "Сидоров", "Программист", 80000);

        // добавление сотрудников в хеш-таблицу
        hashTable.insert(employee1);
        hashTable.insert(employee2);
        hashTable.insert(employee3);

        int searchId = 2; // задаём id для поиска сотрудника
        Employee foundEmployee = hashTable.search(searchId); // выполняем поиск сотрудника с заданным id в хеш-таблице
        if (foundEmployee != null) { //выполняем проверку на наличие сотрудника
            System.out.println("Найден сотрудник с ID " + searchId + ": " + foundEmployee); // выводим сообщение
        } else {
            System.out.println("Сотрудник с ID " + searchId + " не найден"); // выводим сообщение
        }

        int removeId = 1; // задаём id для удаления сотрудника
        hashTable.remove(removeId); // удаление сотрудника с заданным id из хеш-таблицы
        System.out.println("Сотрудник с ID " + removeId + " удален"); // выводим сообщение

        Employee notFoundEmployee = hashTable.search(removeId); // проверка удаления, пытаемся найти удаленного сотрудника по id
        if (notFoundEmployee != null) { //проверка наличия найденного сотрудника
            System.out.println("Найден сотрудник с ID " + removeId + ": " + notFoundEmployee);
        } else {
            System.out.println("Сотрудник с ID " + removeId + " не найден");
        }
    }
}
