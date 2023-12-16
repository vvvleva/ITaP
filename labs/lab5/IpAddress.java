import java.util.regex.Pattern;

public class IpAddress {
    public static void main(String[] args) {
        String ipAddress = "192.168.0.1"; // Пример ввода IP-адреса
        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"; //Pегулярное выражение для проверки корректности IP-адреса

        if (Pattern.matches(ipPattern, ipAddress)) {  //Проверка введенного IP-адреса на соответствие регулярному выражению
            System.out.println("Введенный IP-адрес корректен");
        } else {
            System.out.println("Введенный IP-адрес некорректен");
        }
    }
}
