import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkReplacer {
    public static void main(String[] args) {
        String text = "Страница на сайте www.example.com. Там много интересного.";
        Pattern pattern = Pattern.compile("((http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(\\S*))"); //Регулярное выражение для поиска ссылок в тексте
        Matcher matcher = pattern.matcher(text); // Создаем объект Matcher для поиска соответствий в тексте
        String replacedText = matcher.replaceAll("<a href=\"$1\">$1</a>"); // Заменяем найденные ссылки на гиперссылки
        System.out.println(replacedText);  // Выводим измененный текст
    }
}
