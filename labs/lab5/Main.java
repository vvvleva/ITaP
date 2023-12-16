import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String text = "A Christmas tree was born in the forest, it grew in the forest.";
        char letter = 'f'; // Выбираем букву для поиска
        String regex = "\\b" + letter + "\\w*\\b";  // Создаем регулярное выражение для поиска слов, начинающихся с заданной буквы
        Pattern pattern = Pattern.compile(regex); // Компилируем регулярное выражение в объект Pattern
        Matcher matcher = pattern.matcher(text);  // Создаем объект Matcher для поиска соответствий в тексте
        while (matcher.find()) { // Ищем все соответствия и выводим их на экран
            System.out.println(matcher.group());
        }
    }
}
