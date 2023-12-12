// Создаем класс CustomNumberFormatException, который наследуется от класса NumberFormatException
 class CustomNumberFormatException extends NumberFormatException {
    // Конструктор класса, принимающий сообщение об ошибке и передает его в конструктор суперкласса NumberFormatException с помощью ключевого слова super
    public CustomNumberFormatException(String message) {
        super(message);
    }
}
