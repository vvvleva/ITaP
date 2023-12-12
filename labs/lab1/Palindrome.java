public class Palindrome {
    public static String reverseString(String s){
        String reversed = "";  //принимает строку s в качестве параметра
        for (int i = s.length()-1; i>=0; i--){ // цикл for проходит по строке с конца и добавляет каждый символ в новую строку reversed
            reversed += s.charAt(i); //charAt () — возвращает символ из строки по указанному индексу
        }
        return reversed;
    }

    public static boolean isPalindrome(String s) {
        s = s.replaceAll(" ", "").toLowerCase(); // удаление всех пробелов из строки и приводит ее к нижнему регистру с помощью методов replaceAll и toLowerCase
// replaceAll () возвращает новую строку со всеми совпадениями;  toLowerCase производит преобразование строки в нижний регистр
        String reversed = reverseString(s); // функция вызывает обратный вывод строки и сравнивает исходную строку s с обратной reversed
        return s.equals(reversed);
    }

    public static void main(String[] argss) {
        for (int i = 0; i < argss.length; i++) {  //массив строк, который содержит аргументы командной строки, переданные при запуске программы
            String s = argss[i];  //строковая переменная s, принимает значение текущего аргумента командной строки.
            if (isPalindrome(s)){  //для каждого аргумента вызывается функция isPalindrome для проверки является ли выражение полиндромом
                System.out.println(s + " полиндром");
            }else {
                System.out.println(s + " не полиндром");
            }
        }
    }
}
