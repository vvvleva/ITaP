import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(1289396387328L));
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
        System.out.println(sumsUp(new int[]{1, 2, 3, 4, 5}));
        System.out.println(sumsUp(new int[]{1, 2, 3, 7, 9}));
        System.out.println(sumsUp(new int[]{10, 9, 7, 2, 8}));
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[]{"10%"}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));
        System.out.println(caesarCipher("encode", "hello world", 3));
        System.out.println(caesarCipher("decode", "EPQSWX PEWX XEWO!", 4));
        System.out.println(setSetup(5, 3));
        System.out.println(setSetup(7, 3));
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(isNew(3));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
    }

    public static boolean sameLetterPattern(String str1, String str2) {
        if (str1.length() != str2.length()) { // если длины строк не равны, возвращаем false
            return false;
        }

        // Создадим 2 массива для хранения индекса, каждого символа входных строк
        int[] map1 = new int[256];
        int[] map2 = new int[256];

        for (int i = 0; i < str1.length(); i++) { // перебор символов входных строк для проверки равенства их в массивах map1 и  map2
            if (map1[str1.charAt(i)] != map2[str2.charAt(i)]) {  // если они не равны - символы не имеют одинакового шаблона
                return false;
            }
            map1[str1.charAt(i)] = i + 1; // если равны, то обновляются позиции в массивах
            map2[str2.charAt(i)] = i + 1;
        }
        return true;
    }

    //2
    // Метод получает координаты паука и мухи и возвращает путь, который паук должен пройти, чтобы добраться до мухи
    public static String spiderVsFly(String spiderCoordinates, String flyCoordinates) {
        char spiderRing = spiderCoordinates.charAt(0);  // получаем номер кольца, на котором находится паук
        char flyRing = flyCoordinates.charAt(0);  // получаем номер кольца, на котором находится муха
        int spiderRadial = spiderCoordinates.charAt(1) - '0';  // получаем номер радиала, на котором находится паук
        int flyRadial = flyCoordinates.charAt(1) - '0';  // получаем номер радиала, на котором находится муха

        String path = "";  // путь, который будет содержать последовательность координат паука

        while (spiderRing != flyRing || spiderRadial != flyRadial) {  // пока паук или муха не находятся на одной позиции
            if (spiderRing > flyRing) {  // если позиция паука по кольцам больше позиции мухи
                spiderRing--;  // двигаем паука ближе к мухе по кольцам (используя декремент)
                path += spiderRing + "" + spiderRadial + "-";  // добавляем новую координату паука к пути
            } else if (spiderRing < flyRing) {  // если позиция паука по кольцам меньше позиции мухи
                spiderRing++;  // двигаем паука ближе к мухе по кольцам (используя инкремент)
                path += spiderRing + "" + spiderRadial + "-";  // добавляем новую координату паука к пути
            }

            if (spiderRadial > flyRadial) {  // если позиция паука по радиалам больше позиции мухи
                spiderRadial--;  //двигаем паука ближе к мухе по радиалам
                path += spiderRing + "" + spiderRadial + "-";   // добавляем новую координату паука к пути
            } else if (spiderRadial < flyRadial) {  // если позиция паука по радиалам меньше позиции мухи
                spiderRadial++;  // двигаем паука ближе к мухе по радиалам
                path += spiderRing + "" + spiderRadial + "-";  // добавляем новую координату паука к пути
            }
        }
        return path + flyCoordinates; // добавляем координаты мухи в конец пути
    }

    //3
    public static int digitsCount(long number) { // Функция для подсчёта кол-ва цифр числа
        if (number == 0) // если кол-во цифр = 0, возвращаем 0
            return 0;
        return 1 + digitsCount(number / 10); // прибавляем 1 к счётчику, когда число делится на 10
    }

    // 4
    public static int totalPoints(String[] args, String word) { // Метод для вычисления общего количества очков
        int res = 0; // Итоговое количество очков
        for (int i = 0; i < args.length; i++) {
            int score = args[i].length() == 6 ? 54 : args[i].length() - 2; // Подсчет кол-ва очков 6 букв 54 очка

            for (int j = 0; j < args[i].length(); j++) {  // Определение набранных очков для текущего слова в зависимости от его длины
                if (args[i].indexOf(args[i].charAt(j)) == -1) {  // Если текущая буква не встречается в слове, выходим из цикла
                    break;
                }
            }
            res = res + score; // Прибавляем набранные очки к общему количеству очков
        }
        return res; // Возвращаем общее количество очков
    }

    // 5
    public static ArrayList<ArrayList<Integer>> sumsUp(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int indexRes = 0;  //использоваться для отслеживания индекса в res
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();  //который будет использоваться для хранения чисел и их количества
        for (int i = 0; i < arr.length; i++) {  // пробегает по каждому элементу массива arr
            boolean b = true;  //для проверки
            for (Integer key : map.keySet()) {
                if (key + arr[i] == 8) {  //сумма ключа и текущего элемента arr[i] равна 8
                    ArrayList<Integer> cl = new ArrayList<Integer>();  //создается новый ArrayList с именем cl
                    cl.add(0, Math.min(key, arr[i]));  //минимальное значение между ключом и arr[i]
                    cl.add(1, Math.max(key, arr[i]));  //максимальное значение
                    res.add(indexRes, cl);  //ArrayList добавляется в res по индексу indexRes
                    indexRes++;   //индекс увеличивается на 1
                    map.remove(key, 1);  //map удаляется ключ со значением 1
                    b = false;   //переменная b устанавливается в false и происходит выход из вложенного цикла
                    break;
                }
            }
            if (b)  //b остается true, то в map добавляется новый ключ arr[i] со значением 1
                map.put(arr[i], 1);
        }
        return res;
    }

    // 6
    public static String takeDownAverage(String[] arr) {
        if (arr.length == 0)  //проверяем длину массива
            return "0%";
        double sum = 0;  //будет суммироватся общее количество процентов из массива arr
        for (String str : arr) {
            int number = Integer.parseInt(str.split("%")[0]);  //каждый элемент разделяется с помощью метода split("%") на число и знак "%" и преобразуем в тип int
            sum += number;  //добавляем
        }
        double avg = sum / arr.length;   //вычисляется среднее значение avg путем деления суммы sum на длину массива arr
        double res = (arr.length + 1) * (avg - 5) - sum;  //вычисляется оценка, которую необходимо получитья в следующий раз
        return Integer.toString((int) Math.round(res)) + "%";  //возвращается строка, состоящая из округленной до целого числа оценки и символа "%"
    }

    // 7
    public static String caesarCipher(String mode, String str, int shift) {  // Метод для шифрования и дешифрования строки с использованием шифра Цезаря
        if (mode == "decode")  // Если режим декодирования, умножаем смещение на -1
            shift *= -1;

        str = str.toUpperCase();  // Преобразуем строку в верхний регистр

        String res = "";  // Переменная для хранения результирующей строки

        for (int i = 0; i < str.length(); i++) {  // Проходим по символам в строке
            int sim = str.codePointAt(i);  // Получаем код символа

            int number = sim + shift; // Вычисляем новый код символа

            if (sim >= 65 && sim <= 90) {  // Если символ является буквой от A до Z
                if (number < 65) {  // Если новый код символа меньше 65 (A), то смещаем его на конечный отрезок (Z + 1 - число смещения)
                    number = 91 - (65 - number);
                } else if (number > 90) {  // Если новый код символа больше 90 (Z), то смещаем его на начальный отрезок (A - 1 + число смещения)
                    number = 64 + (number - 90);
                }

                res += Character.toString((char) number);  // Преобразуем новый код символа в символ и добавляем его к результирующей строке
            }
            else {  // Если символ не является буквой от A до Z, добавляем его без изменений
                res += Character.toString((char) sim);
            }
        }
        return res;
    }

    // 8
    public static int factorial(int n) {  // Метод для нахождения факториала
        if (n == 0) {  // если число 0, факториал = 1
            return 1;
        }
        return n * factorial(n - 1);  // рекурсивный вызов метода, умноженный на n
    }
    public static int setSetup(int n, int k) {  // Метод для нахождения количества перестановок из n(общее кол-во элем) по k(кол-во эл которые нужно установить)
        if (n < k) { // если общее количество элементов меньше количества элементов, которые нужно установить, возвращаем 0
            return 0;
        }
        return factorial(n) / factorial(n - k); // возвращаем результат деления факториала n на факториал (n-k)
    }

    // 9
    private static final Map<String, Duration> cityOffsets = new HashMap<>(); // создаем и заполняем статический словарь cityOffsets
    static { //Duration служит для хранения продолжительности времени на основе секунд, наносекуд, дней, месяцев и др промежутков времени
        cityOffsets.put("Los Angeles", Duration.ofHours(-8));
        cityOffsets.put("New York", Duration.ofHours(-5));
        cityOffsets.put("Caracas", Duration.ofHours(-4).plusMinutes(-30));
        cityOffsets.put("Buenos Aires", Duration.ofHours(-3));
        cityOffsets.put("London", Duration.ofHours(0));
        cityOffsets.put("Rome", Duration.ofHours(1));
        cityOffsets.put("Moscow", Duration.ofHours(3));
        cityOffsets.put("Tehran", Duration.ofHours(3).plusMinutes(30));
        cityOffsets.put("New Delhi", Duration.ofHours(5).plusMinutes(30));
        cityOffsets.put("Beijing", Duration.ofHours(8));
        cityOffsets.put("Canberra", Duration.ofHours(10));
    }

    public static String timeDifference(String cityA, String timestamp, String cityB) { // Возвращает разницу во времени между двумя городами, используя переданный timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.US); // создаем объект с указанным шаблоном

        LocalDateTime dateTimeA = LocalDateTime.parse(timestamp, formatter); // из строки timestamp методом parse собирает объект dateTimeA класса LocalDateTime с использованием созданного ранее formatter

        Duration offsetA = cityOffsets.getOrDefault(cityA, Duration.ZERO); // получает значения из словаря, если нет то присваивает Duration.ZERO
        LocalDateTime dateTimeB = dateTimeA.plus(offsetA); // добавляется смещение и получается новый объект

        Duration offsetB = cityOffsets.getOrDefault(cityB, Duration.ZERO); // Получаем смещение времени для cityB из словаря cityOffsets. Если город не найден, присваиваем смещение Duration.ZERO
        dateTimeB = dateTimeB.plus(offsetB); // Добавляем смещение offsetB к dateTimeB

        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"); // создаем новый объект newFormatter для форматирования даты и времени
        return dateTimeB.format(newFormatter); // форматируем в строку
    }

    // 10
    public static boolean isNew(int num) {
        String str = String.valueOf(num);  // преобразуем в строку
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) < str.charAt(0)) {  // если текущий символ не равен нулю и меньше первого встретившегося символа строки
                return false;  // возвращаем  false
            }
        }
        return true;
    }
}
