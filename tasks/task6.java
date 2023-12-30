import java.util.Arrays;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"+ "\n"));
        System.out.println(Arrays.toString(collect("intercontinentalisationalism", 6)));
        System.out.println(Arrays.toString(collect("strengths", 3 )));
        System.out.println(nicoCipher("iloveher", "612345"+ "\n"));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4,15}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 3, 5}, 45)));
        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"+ "\n"));
        System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)"));
        System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)") + "\n");
        System.out.println(isValid("aabbc"));
        System.out.println(isValid("abcdefghhgfedecba") + "\n");
        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
    }

    public static String hiddenAnagram(String sentence, String word) {
        // Приводим обе строки к нижнему регистру и удаляем из них все не-буквенные символы
        sentence = sentence.toLowerCase().replaceAll("[^a-z]", "");
        word = word.toLowerCase().replaceAll("[^a-z]", "");

        char[] wordArr = word.toCharArray();  // Преобразуем слово в массив символов для удобства работы
        List<String> anagrams = new ArrayList<>();  // Создаем список для хранения всех возможных анаграмм из предложения

        // Проходим по предложению, выделяя подстроки такой же длины, как и слово
        for (int i = 0; i <= sentence.length() - word.length(); i++) { // перебор всех возможных подстрок длиной word.length() в строке sentence
            String sub = sentence.substring(i, i + word.length()); // Для каждой подстроки происходит сортировка символов и сравнение с отсортированным массивом символов слова word
            char[] subArr = sub.toCharArray();
            Arrays.sort(subArr);
            Arrays.sort(wordArr);
            if (Arrays.equals(subArr, wordArr)) { // Если подстрока является анаграммой слова word, то она добавляется в список anagrams
                anagrams.add(sub);
            }
        }

        // Если список анаграмм не пустой, возвращаем первую найденную анаграмму
        if (!anagrams.isEmpty()) {
            return anagrams.get(0);
        } else {
            return "notfound";
        }
    }

    // 2
    public static String[] collect(String word, int n) {
        List<String> slices = new ArrayList<>(); // Создаем список для хранения срезов слова
        for (int i = 0; i <= word.length() - n; i++) { // Проходим по слову, выделяя срезы длины n
            slices.add(word.substring(i, i + n)); // Добавляем срез в список
        }
        Collections.sort(slices); // Сортируем срезы
        return slices.toArray(new String[0]); // Преобразуем список в массив строк и возвращаем его
    }

    // 3
    public static String nicoCipher(String message, String key) {
        Map<Character, Integer> keyMap = new HashMap<>(); // хранение номеров букв из ключа
        for (int i = 0; i < key.length(); i++) { // Проходим по ключу
            keyMap.put(key.charAt(i), i + 1); // Присваиваем каждой букве номер и сохраняем в HashMap
        }

        char[] messageArray = message.toCharArray(); // Преобразуем сообщение в массив символов
        Arrays.sort(messageArray); // Сортируем символы сообщения

        List<StringBuilder> columns = new ArrayList<>(); // Создаем список для хранения столбцов сообщения
        for (int i = 0; i < key.length(); i++) { // Проходим по ключу
            columns.add(new StringBuilder()); // Добавляем пустой столбец в список
        }

        int index = 0;
        for (char c : messageArray) { // Проходим по отсортированным символам сообщения
            columns.get(index).append(c); // Добавляем символ в соответствующий столбец
            index = (index + 1) % key.length(); // Переходим к следующему столбцу
        }

        StringBuilder result = new StringBuilder();
        for (char c : keyMap.keySet()) { // Проходим по буквам ключа
            int column = keyMap.get(c) - 1; // Получаем номер столбца для данной буквы
            result.append(columns.get(column)).append(" "); // Добавляем содержимое столбца в результат с пробелом
        }

        return result.toString().trim(); // Возвращаем результат, удаляя лишний пробел в конце
    }

    // 4
    public static int[] twoProduct(int[] arr, int n) {
        for (int i = 0; i < arr.length - 1; i++) { // Проходимся по всем элементам массива
            int multiplier = n / arr[i]; // Множитель равный n поделенному на текущий элемент
            for (int j = i + 1; j < arr.length; j++) { // Ищем множитель в правой части массива
                if (arr[j] == multiplier && arr[i] * arr[j] == n) { // Если найден множитель и произведение соответствует n
                    return new int[]{arr[i], arr[j]}; // возвращаем пару чисел
                }
            }
        }
        return new int[0]; // Если совпадение не найдено, возвращаем пустой массив

    }
    //5
    public static int[] isExact(int f, int m, int n) {  // Если f меньше n, то вызываем isExact с новыми параметрами
        return (f < n) ? isExact(f * (m + 1), m + 1, n) : new int[]{f, m}; // рекурсивный метод вызывает саму себя в зависимости от условия (f < n).
    }  // Иначе возвращаем массив из значений f и m

    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);  // Вызываем isExact и сохраняем результат в переменную res
        return (res[0] == n) ? res : new int[]{};// Если первый элемент массива res равен n, то возвращаем res, иначе возвращаем пустой массив
    }

    //6
    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');  // Находим индекс открывающей скобки
        if (startBracket != -1) { // Если найдена открывающая скобка
            String repeating = frac.substring(startBracket + 1, frac.length() - 1);  // Получаем повторяющуюся часть дроби
            frac = frac.substring(0, startBracket) + repeating.repeat(9 - repeating.length());  // Заменяем повторяющуюся часть на ее повторение до 9 символов
        }
        double a = Double.parseDouble(frac);  // Преобразуем строку в число с плавающей точкой
        int div = 2;  // делитель
        while (Math.ceil(a * div) - a * div > 0.000001)
            div++; // Пока разница между округленным и исходным значениями больше 0.000001, увеличиваем делитель
        return (int) Math.ceil(a * div) + "/" + div; // Возвращаем округленное значение и делитель в виде строки
    }

    //7
    public static String pilish_string(String str) {
        // Инициализируем переменные для результата, значения числа Пи, индексы для строки и числа Пи
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0, strIndex = 0;

        while (strIndex < str.length()) {  // Пока не пройдена вся строка
            int p = pi.charAt(piIndex++) - '0';  // Получаем цифру числа Пи
            int n = Math.min(p, str.length() - strIndex);  // Вычисляем длину подстроки, которая будет добавлена к результату
            res += str.substring(strIndex, strIndex + n); // Добавляем подстроку к результату
            strIndex += n;  // Увеличиваем индекс строки на длину добавленной подстроки
            if (strIndex < str.length()) res += ' ';  // Если строка не пройдена полностью, добавляем пробел к результату
            else // Если строка пройдена полностью, повторяем последний символ до достижения длины числа Пи
                while (n++ < p) res += res.charAt(res.length() - 1);
        }
        return res; // Возвращаем результат
    }

    //8
    public static String generateNonconsecutive(String str) {
        Pattern part = Pattern.compile("^( [\\-+*/] )*(\\()*(-*\\d+)(\\))*");  // Создаем шаблон для поиска подстрок, соответствующих математическим операциям

        // Инициализируем переменные для текущего узла и начального уровня скобок
        boolean start = true;
        Node currentNode = new Node("+");
        currentNode.setLeft(new Node("0"));
        int parLevel = 0;

        for (int i = 0; i < str.length(); ) {  // Проходим по строке
            Matcher matcher = part.matcher(str).region(i, str.length()); // Создаем Matcher для поиска подстрок, соответствующих шаблону, начиная с позиции i

            if (!matcher.find()) { // Если не найдено соответствие шаблону, выводим сообщение об ошибке и возвращаем null
                System.out.println("Syntax error");
                return null;
            }

            if (matcher.group(1) == null) { // Если не найдена операция, добавляем правый узел к текущему узлу
                if (!start) {
                    System.out.println("Syntax error");
                    return null;
                }
                currentNode.setRight(new Node(matcher.group(3)));
                start = false;
                i = matcher.end();
                if (!(matcher.group(2) == null)) {
                    parLevel++;
                }
                continue;
            }
            // Создаем узел для операции и числа
            Node opNode = new Node(String.valueOf(matcher.group(1).charAt(1)));
            Node numNode = new Node(matcher.group(3));
            opNode.setRight(numNode);
            if (parLevel > 0) {  // Обрабатываем случаи с уровнями скобок
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/') {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                } else {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                    currentNode = opNode;
                }
            }
            if (parLevel == 0) {
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/') {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                } else {
                    opNode.setLeft(currentNode);
                    currentNode = opNode;
                }
            }

            if (!(matcher.group(2) == null)) { // Обновляем текущий узел и уровень скобок
                currentNode = opNode;
                parLevel++;
            }
            if (!(matcher.group(4) == null)) {
                if (!(currentNode.root == null)) {
                    currentNode = currentNode.root;
                }
                parLevel--;
            }
            i = matcher.end();
        }
        try {  // Вычисляем результат и возвращаем его в виде строки
            double res = currentNode.getUltimateRoot().computeNode();
            if (res % 1 == 0) {
                return String.valueOf((int) res);
            }
            return String.valueOf(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    static class Node { // Класс Node представляет узел в дереве выражения
        String value; // Поле хранит значение узла

        // Остальные 3 поля хранят ссылки на левого потомка, родителя и правого потомка соответственно
        Node left;
        Node root;
        Node right;

        public void setLeft(Node node) { // Метод устанавливает левого потомка узла и устанавливает текущий узел как родителя для установленного потомка
            this.left = node;
            node.root = this;
        }

        public void setRight(Node node) { // Метод устанавливает правого потомка узла и устанавливает текущий узел как родителя для установленного потомка
            this.right = node;
            node.root = this;
        }

        public Node getUltimateRoot() { // Метод возвращает корневой узел дерева выражения
            if (root != null) {
                return root.getUltimateRoot();
            }
            return this;
        }

        public double computeNode() throws Exception { // Метод computeNode рекурсивно вычисляет значение узла и его потомков, используя операции +, -, *, /
            if ("+-*/".contains(value)) {
                double num1 = left.computeNode();
                double num2 = right.computeNode();

                switch (value) {
                    case "+":
                        return num1 + num2;
                    case "-":
                        return num1 - num2;
                    case "*":
                        return num1 * num2;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        return num1 / num2;
                    default:
                        throw new Exception("Syntax error");
                }
            }
            return Double.parseDouble(value);
        }
        Node(String value) { // Конструктор Node инициализирует узел с заданным значением, без потомков и родителя
            this.value = value;
            this.root = null;
            right = null;
            left = null;
        }
    }

    // 9
    public static String isValid(String str) {
        int[] charCounts = new int[26]; // Создаем массив charCounts для подсчета количества каждой буквы в строке
        for (char c : str.toCharArray()) {  // Проходим по каждому символу в строке и увеличиваем соответствующий элемент массива charCounts
            charCounts[c - 'a']++;
        }
        int prevCount = -1; // Ззначение -1
        int removals = 0; // Подсчет удалений
        for (int count : charCounts) { // Проходим по массиву и сравниваем количество букв
            if (count > 0) {
                if (prevCount == -1) { // Если prevCount равен -1, присваиваем ему текущее количество букв
                    prevCount = count;
                } else if (prevCount != count) { // Иначе сравниваем с предыдущим количеством
                    removals += Math.abs(prevCount - count);
                    if (removals > 1) return "NO"; // Если разница между количествами больше 1, возвращаем "NO"
                }
            }
        }
        return "YES";
    }

    // 10
    public static String findLCS(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];  // Создаем двумерный массив dp для хранения длины наибольшей общей подпоследовательности
        StringBuilder lcs = new StringBuilder(); // Создаем StringBuilder lcs для хранения наибольшей общей подпоследовательности

// Проходим по строкам и столбцам dp, заполняя их значениями в зависимости от совпадения символов
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int i = s1.length(), j = s2.length(); // Инициализируем переменные i и j значением длины строк s1 и s2
        while (i > 0 && j > 0) {  // Пока i и j больше 0, добавляем совпадающие символы в lcs и уменьшаем i и j в зависимости от значений dp
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString(); // Возвращаем наибольшую общую подпоследовательность, развернутую в обратном порядке
    }
}
