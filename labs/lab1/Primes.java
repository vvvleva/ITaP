public class Primes {
    public static void main(String[] args) {
        System.out.println("Простое число: ");
        for (int i = 2; i < 100; i++) {  //b++ - увеличение значения b на 1 и возврат значения b до этого увеличения
            if (isPrime(i)) {                 //++b - увеличение значения b на 1 и возврат нового значения
                System.out.print(i + " ");
            }
        }
    }
    public static boolean isPrime(int n) {
            if (n <= 1) {  //Натуральное число является простым, если оно отлично от 1 и делится без остатка только на 1 и на само себя
                return false;
            }
            for (int i = 2; i*i <=n; i++){  // если число делится нацело на какое либо число от 2 до sgrt(n), оно не является простым
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
