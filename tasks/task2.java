import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println(duplicateChars("donald"));
        System.out.println(duplicateChars("orange"));
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));
        System.out.println(differenceEvenOdd(new int[]{44, 32, 86, 19}));
        System.out.println(differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55}));
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 5}));
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 6}));
        System.out.println(Arrays.toString(indexMult(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(indexMult(new int[]{3, 3, -2, 408, 3, 31})));
        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));
        System.out.println(Tribonacci(7));
        System.out.println(Tribonacci(11));
        System.out.println(pseudoHash(5));
        System.out.println(pseudoHash(11));
        System.out.println(botHelper("Hello, I’m under the water, please help me"));
        System.out.println(botHelper("Two pepperoni pizzas please"));
        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
    }


    public static boolean duplicateChars(String str) {
        int[] charCount = new int [256]; // Создаем массив для подсчёта кол-ва встречающихся символов
        for (int i = 0; i < str.length(); i++){ // Проходим увеличивая счётчик для каждого символа
            charCount[str.charAt(i)]++;
                 if (charCount[str.charAt(i)]>1)//Если символ встречается >1 раза, возвращаем true
                return true;
            }
        return false;   // Если нет повторений, возвращаем false
    }


    //2
    public static String getInitials(String name) {
        char[] getInitials = new char[256];
        StringBuilder initials = new StringBuilder();
        String[] words = name.split(" ");  //прием строки с фамилией и именем
// с помощью split строка делится на слова по пробелам, сохраняя слова в массив words
        for (String word : words) {
            if (!word.isEmpty()) { //если слово не пустое
                initials.append(Character.toUpperCase(word.charAt(0)));// 1 буква с помощью метода append добавляется к инициалам с помощью метода append
            }
        }
        return initials.toString(); //Инициалы преобразуются в строку с помощью метода toStrig
    }


    // 3
    public static int differenceEvenOdd(int[] nums) {
        int differ = 0;  // прием чисел в массив
        for (int num : nums)  // на каждой итерации очередной элемент копируется в переменную x.
            if (num % 2 == 0) {  // проверка на четность
                differ += num; // если число четное, оно добавляется к переменной differ
            } else {
                differ -= num;  // если число нечетное оно вычитается из переменной differ
            }
        return Math.abs(differ); // Модуль из отриц в положительное
    }


    //4
    public static boolean equalToAvg(int[] nums) {
        int equal = 0;   // прием чисел в массив
        for (int num : nums) { // на каждой итерации очередной элемент копируется в переменную
            equal += num; // сложение чисел в массиве
        }
        double avg =(double) equal / nums.length; // деление суммы чисел массива на их кол-во
        for (int num : nums) {
            if (num == avg) {  // равняется ли хоть один элемент среднему арифметическому
                return true;
            }
        }
        return false;
    }

    //5
    public static int[] indexMult(int[] nums) {
        int[] mult = new int[nums.length]; // создаём новый массив одинаковой длины с nums

        for (int i = 0; i < nums.length; i++) { // проходим по каждому элементу
            mult[i] = nums[i] * i;  //после каждой итерации элемент массива умножается на его индекс i, результат записывается в mult
        }
        return mult;
    }

    //6
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();  // разворачиваем и преобразуем фразы в строку
    }

    //7
    public static int Tribonacci(int n) { // метод реализует последовательность Трибоначчи
        if (n == 0 || n == 1) {  // прописываем вывод последовательности для исключений
            return 0;
        } else if (n == 2) {
            return 1;
        }
        int a=0,  b=0, c=1,d;
        for (int i=3; i <=n-1; i++){
            d=a+b+c;
            a=b;
            b=c;
            c=d;
        }
        return c;
    }

    //8   Хеш-функция - функция, осуществляющая преобразование массива входных данных произвольной длины в выходную битовую строку установленной длины
    public static String pseudoHash(int lenght) { // метод принимает длину хэша как аргумент
        String abc = "abcdef0123456789";
        StringBuilder pseudo = new StringBuilder(lenght); // Создаем объект, который используется для построения строки квази-хэша. Квази-хэш - функция, которая преобразует входные данные произвольной длины в фиксированную строку фиксированной длины. В отличие от обычных хэш-функций, квази-хэш функции не гарантируют уникальность и равномерное распределение значений.
        Random random = new Random(); // создаем объект Random, для генерации случайных чисел

        for (int i = 0; i < lenght; i++) {
            int index = random.nextInt(abc.length()); // генерация случайных символов, с помощью выбора случайного индекса из строки abc
            char randomChar = abc.charAt(index); // добавляем этот случайный символ в строку
            pseudo.append(randomChar);
        }
        return pseudo.toString(); // с помощью метода преобразуем квази-хэш в строку
    }

    //9
    public static String botHelper(String phrase) {
        if (phrase.contains("help")) { //метод contains() проверяет есть ли в строке данное слово
            return "Calling for a staff member";
        } else {
            return "Keep waiting";

        }
    }

   // 10
   public static boolean isAnagram(String str1, String str2) {
       // Удаление пробелов и приведение к нижнему регистру с помощью методов replaceAll("\\s", "") и toLowerCase()
       str1 = str1.replaceAll("\\s", "").toLowerCase();
       str2 = str2.replaceAll("\\s", "").toLowerCase();

       // Проверка длины строк
       if (str1.length() != str2.length()) { //у анаграмм должна быть одинаковая длина, иначе строки нне являются анаграммами
           return false;
       }

       // Преобразование строк в массивы символов
       char[] charArray1 = str1.toCharArray();
       char[] charArray2 = str2.toCharArray();

       // Сортировка массивов символов
       Arrays.sort(charArray1);
       Arrays.sort(charArray2);

       // Сравнение отсортированных массивов символов
       return Arrays.equals(charArray1, charArray2); // если массивы равны, то строки являются анаграммами и функция возвращает true, в противном случае - false
   }
}
