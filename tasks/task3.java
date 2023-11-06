import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(replaceVovels("apple"));
        System.out.println(replaceVovels("Even if you did this task not by yourself, you have to understand every single line of code."));
        System.out.println(stringTransform("hello"));
        System.out.println(stringTransform("bookkeeper"));
        System.out.println(doesBlockkFit(1, 8, 1, 1, 1));
        System.out.println(doesBlockkFit(1, 2, 2, 1, 1));
        System.out.println(numCheck(243));
        System.out.println(numCheck(52));
        System.out.println(countRoots(new int[]{1, -3, 2}));
        System.out.println(countRoots(new int[]{4, 5, 2}));
        System.out.println(countRoots(new int[]{1, -6, 9}));
        System.out.println(salesData(new String[][]{
                {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}
        }));
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(validSplit("cat dog goose fish"));
        System.out.println(commonVovel("Hello world"));
        System.out.println(commonVovel("Actions speak louder than words."));
        System.out.println(waveForm(new int[]{3, 1, 4, 2, 7, 5}));
        System.out.println(waveForm(new int[]{1, 2, 3, 3, 3, 2, 4, 5}));
        System.out.println(Arrays.deepToString(dataScience(new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {5, 5, 5, 5, 5},
                {7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1}
        })));
        System.out.println(Arrays.deepToString(dataScience(new int[][]{
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}
        })));
    }

    public static String replaceVovels(String input) {
        String vowels = "aeiouAEIOU"; // перечисление всех гласных
        StringBuilder star = new StringBuilder();  // создание ообъекта для формирования изменений строки
        for (int i = 0; i < input.length(); i++) { // проходим по каждому символу строки проверяя на гласную букву
            char c = input.charAt(i);  // любой символ (кроме гласной) записывается в StringBuilder без изменений

            if (vowels.contains(String.valueOf(c))) {  // если символ является гласной буквой, то в StringBuilder заменяем его *
                star.append("*");
            } else {
                star.append(c); // иначе добавляем сам символ без изменений
            }
        }
        return star.toString();
    }

    //2
    public static String stringTransform(String s) {
        String resultString2 = replaceDoublePattern(s);  // Вызов метода replaceDoublePattern, который заменяет повторяющиеся символы в строке s и сохраняет результат в переменную resultString2
        return resultString2;  // Возврат преобразованной строки
    }

    public static String replaceDoublePattern(String input) {
        // Создаем регулярное выражение для поиска двух идущих подряд букв
        String doublePatternRegex = "(.)(\\1)";

        // Заменяем двойные буквы на "Double*"
        String result = input.replaceAll(doublePatternRegex, "Double$1");

        return result;
    }

    //3
    public static boolean doesBlockkFit(int a, int b, int c, int w, int h) {
        int s = 2 * (a * b + a * h + b * h);  // Площадь параллелепипеда
        if (a * b <= s && b * c <= s && c * a <= s) { // проверка по площади
            if (c <= w && c <= h) { // проверка по глубине
                return true;
            }
        }
        return false;
    }

    //4
    public static boolean numCheck(int num) {
        int sum = 0; // начало отсчёта
        int temp = num; // значение переданного числа
        while (temp > 0) {  // цикл начнется, если число будет больше 0
            int digit = temp % 10; // разбиение числа на отдельные цифры
            sum += digit * digit; // возведение в квадрат каждой отдельной цифры, которая добавляется к переменной sum
            temp /= 10; // делим число на 10, чтобы убрать последнюю цифру
        }
        if (sum % 2 == num % 2) {  // если сумма квадратов цифр введенного числа и полученного числа одинаково четны/нечетны,функция возвращает true или false
            return true;
        } else {
            return false;
        }
    }

    //5
    public static int countRoots(int[] ratio) {
        if (ratio.length != 3) {
            System.out.println("В массиве меньше 3-х элементов, расчет не возможен");
            return -3;
        }
        // Всё для дискриминанта
        int a = ratio[0];
        int b = ratio[1];
        int c = ratio[2];
        int discrim = b * b - 4 * a * c;

        // Определяем кол-во корней
        if (discrim > 0) {
            return 2;  // 2 действительных корня
        } else if (discrim == 0) {
            return 1;  //1 повторяющийся корень
        } else {
            return 0;  // корней нет
        }
    }

    //6
    public static ArrayList<String> salesData(String[][] salesData) {
        // Создаем список для хранения товаров, которые были проданы во всех магазинах
        ArrayList<String> products = new ArrayList<>(); // Создаём пустой список для хранения товаров
        int maxShopCount = 0; // пременная для хранения максимального кол-ва магазинов, в которых был продан данный товар

        // Начинаем перебирать строки во входном массиве
        for (String[] shops : salesData) {
            int shopCount = shops.length; // Определяем кол-во магазинов для текущего товара

            if (shopCount > maxShopCount){  // если кол-во товара больше, чем  maxShopCount,
                maxShopCount = shopCount;  //то maxShopCount обновляется и
                products.clear(); // список очищается
                products.add(shops[0]);  // + добавляется новый товар
            } else if (shopCount == maxShopCount) {  //сли количество магазинов равно maxShopCount
                products.add(shops[0]);  // то товар добавляется в список products
            }
        }
        return products;
    }


    //7
    public static boolean validSplit(String s) {
        String[] words = s.split("\\s+");  // Разбиваем предложение на слова

        if (words.length == 0) {
            return false; // Проверка на пустое предложение
        }
        String last = Character.toString(words[0].charAt(words[0].length() - 1)); // Получаем последнюю букву первого слова с помощью (charAt () — возвращает символ, расположенный по указанному индексу строки) проходя по длине слова

        for (int i = 1; i < words.length; i++) {  // цикл for проходит по строке с начала
            String first = Character.toString(words[i].charAt(0)); // Получаем первую букву текущего слова
            if (!last.equalsIgnoreCase(first)) { // Если последняя буква предыдущего слова не равна первой букве текущего слова
                return false; // Нельзя разбить предложение
            }
            last = Character.toString(words[i].charAt(words[i].length() - 1)); // Получаем последнюю букву текущего слова
        }
        return true;
    }

    //8
    public static boolean waveForm(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {  // проходимся по каждому элементу массива
            if (arr[i] > arr[i + 1]) {   // проверяем, является ли текущий элемент больше следующего
                if (arr.length-1 < arr[i]) {  // если да, то проверяем, является ли следующий элемент меньше предыдущего
                    return true;  // если да, то массив является волнообразным
                }
            } else if (arr[i] < arr[i + 1]) {  // проверяем, является ли текущий элемент меньше следующего
                if (arr[i + 1] > arr[i]) {   // если да, то проверяем, является ли следующий элемент больше предыдущего
                    return false;   // если нет, то массив не является волнообразным
                }
            }
            // если ни одно из условий не выполнилось, то продолжаем цикл
        }
        // если цикл завершился без возвращения false, значит массив является волнообразным
        return true;
    }

    //9
    public static char commonVovel(String s) {
        String vowels = "aeiouAEIOU";  // перечисление гласных
        StringBuilder result = new StringBuilder();  // создание ообъекта для формирования изменений строки
        int[] counts = new int[60];   // создаем массив для хранения количества встреч каждой гласной буквы

        for (int i = 0; i < s.length(); i++) { // проходим по каждому символу строки проверяя на гласную букву
            char c = s.charAt(i);  // любой символ (кроме гласной) записывается в StringBuilder без изменений
            c = Character.toLowerCase(c); // Глассные в верхнем регистре
            if (vowels.contains(String.valueOf(c))) {  //если символ является гласной буквой, то увеличиваем счётчик
                int index = vowels.indexOf(c);   // находим индекс гласной буквы в строке vowels
                counts[index]++;  // увеличиваем счетчик для этой буквы на 1
            }
        }
        // находим индекс максимального значения в массиве counts
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return vowels.charAt(maxIndex);  //возвращаем гласную, которая встречалась чаще всего
    }

    //10
    public static int[][] dataScience(int[][] array) {
        int n = array.length;  // определяем длину массива

        // Проходим по каждой строке матрицы, mainRow - текущая строка главной диагонали
        for (int mainRow = 0; mainRow < n; mainRow++) {
            int newElement = 0;

            // Проходим по каждой строке матрицы, sideRow - остальные строки матрицы
            for (int sideRow = 0; sideRow < n; sideRow++) {
                // Проверяем, что текущая строка не совпадает с главной диагональю
                if (sideRow != mainRow) {
                    // Суммируем элементы из разных строк в столбце, кроме элемента на главной диагонали
                    newElement += array[sideRow][mainRow];
                }
            }
            // Заменяем элемент на главной диагонали на новое значение
            array[mainRow][mainRow] = (int) Math.round((double) newElement / (n - 1));
        }
        return array;
    }
}
