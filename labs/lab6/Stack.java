public class Stack<T> {
    private T[] data; // Массив для хранения элементов стека
    private int size; // Переменная для отслеживания количества элементов в стеке

    // Конструктор класса, принимающий вместимость стека
    public Stack(int capacity) {
        data = (T[]) new Object[capacity]; // Создание массива указанной вместимости
        size = 0; // Инициализация переменной size значением 0
    }

    // Метод для добавления элемента в стек
    public void push(T element) {
        if (size < data.length) { // Проверка, наличия места для добавления элемента в стек
            data[size] = element; // Добавление элемента в стек
            size++; // Увеличение размера стека
        } else {
            // Если стек полон, можно реализовать увеличение его размера или генерацию исключения
        }
    }

    // Метод для удаления элемента из стека и возврата его значения
    public T pop() {
        if (size > 0) { // Проверка, есть ли элементы в стеке для удаления
            T element = data[size - 1]; // Получение верхнего элемента стека
            data[size - 1] = null; // Удаление элемента из стека
            size--; // Уменьшение размера стека
            return element; // Возврат удаленного элемента
        } else {
            // Если стек пуст, можно реализовать генерацию исключения
            return null;
        }
    }

    // Метод для получения верхнего элемента стека без его удаления
    public T peek() {
        if (size > 0) { // Проверка, есть ли элементы в стеке
            return data[size - 1]; // Возврат верхнего элемента стека
        } else {
            // Если стек пуст, можно реализовать генерацию исключения
            return null;
        }
    }

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }
}
