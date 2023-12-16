import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    public static void main(String[] args) {
        String password = "Password321123"; // здесь можно изменить пароль для проверки

        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$"); //Метод Pattern.compile - шаблон в кот. заданы символы, которые можно использовать
        Matcher matcher = pattern.matcher(password);  // Объект Matcher используется для поиска соответствий шаблону внутри текста

        if (matcher.matches()) { // Соответствие требованиям
            System.out.println("Пароль корректен");
        } else {
            System.out.println("Пароль некорректен");
        }
    }
}
