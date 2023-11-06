import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));
        System.out.println(generateBrackets(2));
        System.out.println(generateBrackets(3));
        System.out.println(binarySystem(3));
        System.out.println(binarySystem(4));
        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("klmabzyxw"));
        System.out.println(letterCount("aaabbcdd"));
        System.out.println(letterCount("vvvvaajaaaaa"));
        System.out.println(convertToNum(("eight")));
        System.out.println(convertToNum(("five hundred sixty seven")));
        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));
        System.out.println(shortestWay(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1},
        }));
        System.out.println(shortestWay(new int[][]{
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9},
        }));
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));
    }

    public static String nonRepeatable(String str) {
        // при условии что строка пустая или состоит из одного символа
        if (str.length() <= 1) {
            return str;
        }

        // сравнение происходит от 1 элемента, далее если этот элемент больше не встречается в строке, он записывается в вывод, если встречается, то переходит на следующий символ
        // рекурсивный случай: проверяем, есть ли первый символ в оставшейся части строки
        if (str.substring(1).contains(str.substring(0, 1))) {
            // если символ повторяется, вызываем функцию для оставшейся части строки
            return nonRepeatable(str.substring(1));
        } else {
            // если символ не повторяется, добавляем его в результат и вызываем функцию для оставшейся части строки
            return str.substring(0, 1) + nonRepeatable(str.substring(1));
        }
    }

    //2
    public static List<String> generateBrackets(int n) {

        List<String> result = new ArrayList<>();  // Создаём список для хранения комбинаций скобок
        backtrack(result, "", 0, 0, n);  // метод backtrack, который рекурсивно генерирует все возможные комбинации пар скобок, записывает их в result
        return result;  // список, в котором хранятся все правильные комбинации скобок
    }
    // Рекурсивная генерация всех пар скобок
    private static void backtrack(List<String> result, String current, int open, int close, int n) { //open:количество открывающих скобок; close:количество закрывающих скобок; n: заданное число n
        if (current.length() == n * 2) {  //current: текущая комбинация скобок; если длина текущей комбинации скобок равна удвоенному значению n, то добавляем её в список result и завершаем метод
            result.add(current); // удвоение n используется для создания временного массива достаточного размера для хранения этой информации
            return;
        }

        if (open < n) //если кол-во закрывающих скобок (close) меньше open, то вызываем метод backtrack с добавлением закрывающей скобки к текущей комбинации и увеличением значения
            backtrack(result, current + "(", open + 1, close, n);
        if (close < open) //если количество закрывающих скобок (close) больше open, то вызываем метод backtrack с добавлением открывающей скобки к текущей комбинации и увеличением значения
            backtrack(result, current + ")", open, close + 1, n);
    }


    //3
    public static List<String> binarySystem(int n) {
        List<String> combinations = new ArrayList<>(); // Создаём список для хранения комбинаций чисел
        check(n, "", combinations); // метод check, который рекурсивно генерирует все возможные комбинации пар скобок, записывает их в combinations
        return combinations;
    }

    private static void check(int n, String combination, List<String> combinations) { // n - кол-во чисел в выводе, String combinations - текущая комбинация чисел, List<String> combinations - список со всеми комбинациями
        if (combination.length() == n) {   //создание временного массива достаточного размера для хранения информации
            combinations.add(combination);  // добавление комбинации в список
            return;
        }

        check(n, combination + "1", combinations); // функция чек с заданными параметрами

        if (combination.length() == 0 || combination.charAt(combination.length() - 1) != '0') {  //если длина текущей комбинации равна 0 или последняя цифра не равна 0, то вызывается функция check с добавлением 0 в конец текущей комбинации
            check(n, combination + "0", combinations);  // Это позволяет проверить все возможные комбинации чисел, включая те, которые начинаются с нуля
        }
    }

    //4
    public static String alphabeticRow(String str) {
        int maxLen = 0; // переменная для хранения максимальной длины последовательности
        int curLen = 1; // переменная для хранения текущей длины последовательности
        int maxIndex = 0; // переменная для хранения индекса начала самой длинной последовательности

        for (int i = 0; i < str.length() - 1; i++) { // цикл для прохода по всем символам строки
            if (str.charAt(i) + 1 == str.charAt(i + 1)) { // если текущий символ + 1 равен следующему, то это означает, что последовательность продолжается в возрастающем порядке
                curLen++; // увеличиваем текущую длину на 1
            } else { // если текущий символ не равен следующему, то это означает конец текущей последовательности
                if (curLen > maxLen) { // проверяем, если текущая длина больше максимальной
                    maxLen = curLen; // обновляем максимальную длину
                    maxIndex = i - maxLen + 1; // обновляем индекс начала самой длинной последовательности
                }
                curLen = 1; // сбрасываем текущую длину до 1, так как новая последовательность начинается с текущего символа
            }
        }
        for (int i = 0; i < str.length() - 1; i++) { // цикл для прохода по всем символам строки
            if (str.charAt(i) - 1 == str.charAt(i + 1)) { // если текущий символ - 1 равен следующему, то это означает, что последовательность продолжается в убывающем порядке
                curLen++; // увеличиваем текущую длину на 1
            } else { // если текущий символ не равен следующему, то это означает конец текущей последовательности
                if (curLen > maxLen) { // проверяем, если текущая длина больше максимальной
                    maxLen = curLen; // обновляем максимальную длину
                    maxIndex = i - maxLen + 1; // обновляем индекс начала самой длинной последовательности
                }
                curLen = 1; // сбрасываем текущую длину до 1, так как новая последовательность начинается с текущего символа
            }
        }

        if (curLen > maxLen) { // проверяем, если текущая длина больше максимальной после выхода из цикла
            maxLen = curLen; // обновляем максимальную длину
            maxIndex = str.length() - maxLen; // обновляем индекс начала самой длинной последовательности
        }
        return str.substring(maxIndex, maxIndex + maxLen); // возвращаем подстроку с максимальной последовательностью
    }

//5
    public static String letterCount(String str) {
        StringBuilder sb = new StringBuilder(); // создаем StringBuilder для построения новой строки
        char prevChar = str.charAt(0); // сохраняем первый символ строки
        int count = 1; // переменная для хранения количества повторяющихся символов

        for (int i = 1; i < str.length(); i++) { // цикл для прохода по всем символам, начиная со второго
            char curChar = str.charAt(i); // текущий символ

            if (curChar == prevChar) { // если текущий символ равен предыдущему, то увеличиваем счетчик
                count++;
            } else { // иначе добавляем в StringBuilder предыдущий символ и его количество и обновляем prevChar и count
                sb.append(prevChar);  // метод append обновляет значение объекта. Обновляем значение предыдущего символа
                sb.append(count);  // обновляем значение счетчика
                prevChar = curChar; // предыдущий символ будет начинать отсчёт с единицы (заново)
                count = 1;
            }
        }

        // добавляем в StringBuilder последний символ и его количество
        sb.append(prevChar);
        sb.append(count);

        // преобразуем StringBuilder в строку
        String result = sb.toString();

        // форматируем строку в требуемом формате "a3 b4"
        StringBuilder formatResult = new StringBuilder();  // Создаем StringBuilder для построения новой строки
        for (int i = 0; i < result.length(); i += 2) {  // увеличиваем преременную i на 2 после каждой итерации для обработки строки result по 2 символа за раз
            formatResult.append(result.charAt(i));  // Добавляем в строку formatresult символ из строки result с индексом i
            formatResult.append(result.charAt(i + 1)); // Далее добавляем символ с индексом i+1
            formatResult.append(" ");  // добавляем пробел для лучшей читаемости результата
        }
// возвращаем отформатированную строку
        return formatResult.toString().trim(); // Полученную строку преобразуем в тип String с помощью метода toString() и удаляем лишние пробелы с помощью метода trim()
    }

    //6
    public static int convertToNum(String numStr) {
        int result = 0;
        String[] parts = numStr.split(" "); // С помощью метода split разобьём строки на подстроки по пробелу

        for (int i = 0; i < parts.length; i++) {   // проходим по каждому элементу цикла и сравниваем со строкой one, если подстрока равна "one", то к переменной result прибавляется 1
            String part = parts[i];  // так проходим по каждому элементу

            if (part.equals("one")) {
                result += 1;
            } else if (part.equals("two")) {
                result += 2;
            } else if (part.equals("three")) {
                result += 3;
            } else if (part.equals("four")) {
                result += 4;
            } else if (part.equals("five")) {
                result += 5;
            } else if (part.equals("six")) {
                result += 6;
            } else if (part.equals("seven")) {
                result += 7;
            } else if (part.equals("eight")) {
                result += 8;
            } else if (part.equals("nine")) {
                result += 9;
            } else if (part.equals("ten")) {
                result += 10;
            } else if (part.equals("eleven")) {
                result += 11;
            } else if (part.equals("twelve")) {
                result += 12;
            } else if (part.equals("thirteen")) {
                result += 13;
            } else if (part.equals("fourteen")) {
                result += 14;
            } else if (part.equals("fifteen")) {
                result += 15;
            } else if (part.equals("sixteen")) {
                result += 16;
            } else if (part.equals("seventeen")) {
                result += 17;
            } else if (part.equals("eighteen")) {
                result += 18;
            } else if (part.equals("nineteen")) {
                result += 19;
            } else if (part.equals("twenty")) {
                result += 20;
            } else if (part.equals("thirty")) {
                result += 30;
            } else if (part.equals("forty")) {
                result += 40;
            } else if (part.equals("fifty")) {
                result += 50;
            } else if (part.equals("sixty")) {
                result += 60;
            } else if (part.equals("seventy")) {
                result += 70;
            } else if (part.equals("eighty")) {
                result += 80;
            } else if (part.equals("ninety")) {
                result += 90;
            } else if (part.equals("hundred")) {
                result *= 100;
            } else if (part.equals("thousand")) {
                result *= 1000;
            }
        }
        return result;
    }

   //7
    public static String uniqueSubstring(String str) {
        int maxLen = 0; // переменная для хранения максимальной длины последовательности
        int curLen = 1; // переменная для хранения текущей длины последовательности
        int maxIndex = 0; // переменная для хранения индекса начала самой длинной последовательности

        for (int i = 0; i < str.length() - 1; i++) { // цикл для прохода по всем символам строки
            if (str.charAt(i) + 1 == str.charAt(i + 1)) { // если текущий символ + 1 равен следующему, то это означает, что последовательность продолжается в возрастающем порядке
                curLen++; // увеличиваем текущую длину на 1
            } else { // если текущий символ не равен следующему, то это означает конец текущей последовательности
                if (curLen > maxLen) { // проверяем, если текущая длина больше максимальной
                    maxLen = curLen; // обновляем максимальную длину
                    maxIndex = i - maxLen + 1; // обновляем индекс начала самой длинной последовательности
                }
                curLen = 1; // сбрасываем текущую длину до 1, так как новая последовательность начинается с текущего символа
            }
        }
        for (int i = 0; i < str.length() - 1; i++) { // цикл для прохода по всем символам строки
            if (str.charAt(i) - 1 == str.charAt(i + 1)) { // если текущий символ - 1 равен следующему, то это означает, что последовательность продолжается в убывающем порядке
                curLen++; // увеличиваем текущую длину на 1
            } else { // если текущий символ не равен следующему, то это означает конец текущей последовательности
                if (curLen > maxLen) { // проверяем, если текущая длина больше максимальной
                    maxLen = curLen; // обновляем максимальную длину
                    maxIndex = i - maxLen + 1; // обновляем индекс начала самой длинной последовательности
                }
                curLen = 1; // сбрасываем текущую длину до 1, так как новая последовательность начинается с текущего символа
            }
        }

        if (curLen > maxLen) { // проверяем, если текущая длина больше максимальной после выхода из цикла
            maxLen = curLen; // обновляем максимальную длину
            maxIndex = str.length() - maxLen; // обновляем индекс начала самой длинной последовательности
        }

        return str.substring(maxIndex, maxIndex + maxLen); // возвращаем подстроку с максимальной последовательностью
    }

//8
    public static int shortestWay(int[][] matrix) {
        int n = matrix.length; // размер массива
        int[][] min = new int[n][n]; // массив для хранения минимальных путей, размер равен размеру начальной матрицы
        min[0][0] = matrix[0][0]; // первый элемент равен первому элементу исходного массива

    // заполняем первую строку массива min
        for (int i = 1; i < n; i++) {  //цикл for проходит по всем элементам первой строки и столбца. Заполняет их значениями из исходной матрицы, суммируя значения предыдущих элементов
            min[0][i] = min[0][i-1] + matrix[0][i];
        }

    // заполняем первый столбец массива min
        for (int i = 1; i < n; i++) {
            min[i][0] = min[i-1][0] + matrix[i][0];
        }

    // заполняем остальные элементы массива min
        for (int i = 1; i < n; i++) {  // Внешний цикл проходит по всем строкам
            for (int j = 1; j < n; j++) { // Внутренний цикл проходит по всем столбцам
            // выбираем минимальный путь из двух возможных пути сверху или слева от текущей ячейки + значение текущей ячейки в исходной матрице
                min[i][j] = Math.min(min[i-1][j], min[i][j-1]) + matrix[i][j];  //Выбранный путь добавляем к текущему элементу, чтобы получить минимальный путь до текущей ячейки
            }
        }
    // возвращаем значение последнего элемента массива min, которое будет содержать минимальный путь
        return min[n-1][n-1];
    }

//9
    public static String numericOrder(String str) {
        String[] words = str.split(" ");  //Делим строку на слова по пробелу
        String[] orderedWords = new String[words.length]; //Создаем новый массив для хранения слов в правильном порядке

        for (String word : words) { // Проходим по каждому слову в исходной строке
            for (int i = 0; i < word.length(); i++) {  // Ищем числа внутри слова
                if (Character.isDigit(word.charAt(i))) {// Если текущий символ - число
                    int num = Integer.parseInt(String.valueOf(word.charAt(i))); // Получаем значение числа
                    orderedWords[num - 1] = word.substring(0, i) + word.substring(i + 1); // Добавляем слово в правильную позицию в новом массиве
                    break;
                }
            }
        }
        return String.join(" ", orderedWords);// Объединяем все слова из нового массива в одну строку и возвращаем ее
    }

//10
    public static int switchNums(int num1, int num2) {
// Преобразуем числа в массивы символов
        char[] arr1 = String.valueOf(num1).toCharArray();
        char[] arr2 = String.valueOf(num2).toCharArray();

// Сортируем первый массив в порядке возрастания
        Arrays.sort(arr1);
        int n = arr1.length;

        for (int i = 0; i < arr2.length; i++) { // Выполняем итерацию по второму массиву
            for (int j = n - 1; j >= 0; j--) {  //  Находим значение в arr1, которое больше arr2[i]
                if (arr1[j] > arr2[i]) {  //Если arr1[j] > arr2[i]  меняем arr2[i] с arr1[j]
                    arr2[i] = arr1[j];
                    arr1[j] = '0';
                    break;// останавливаем цикл
                }
            }
        }
    return Integer.parseInt(new String(arr2)); // Преобразоваем второй массив обратно в целое число
    }
}
