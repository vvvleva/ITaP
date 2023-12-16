import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "the price of the product is 19.99";  // переменной text присваиваем строку с ценой продукта
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");  // метода pattern.compile для поиска цифр,по шаблону точка и еще цифры (например, 19.99)
        Matcher matcher = pattern.matcher(text);  // объект matcher используется для поиска соответствий шаблону внутри текста
        while (matcher.find()) {  // внутри цикла while matcher находит и выводит каждую, встретившуюся цену
            try {
                double number = Double.parseDouble(matcher.group());
                System.out.println(number);
            } catch (NumberFormatException e) {
                System.err.println("Error: " + matcher.group());
            }
        }
    }
}
