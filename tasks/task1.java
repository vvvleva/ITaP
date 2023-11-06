public class Main {
    public static void main(String args[]) {
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));
        System.out.println(fitCalc(15,1));
        System.out.println(fitCalc(24,2));
        System.out.println(fitCalc(41,3));
        System.out.println(containers(3,4,2));
        System.out.println(containers(5,0,2));
        System.out.println(containers(4,1,4));
        triangleType(5,5,5);
        triangleType(5,4,5);
        triangleType(3,4,5);
        triangleType(5,1,1);
        System.out.println(ternaryEvaluation(8,4));
        System.out.println(ternaryEvaluation(1,11));
        System.out.println(ternaryEvaluation(5,9));
        System.out.println(howManyItems(22, (int) 1.4,2));
        System.out.println(howManyItems(45, (int) 1.8,(int)1.9));
        System.out.println(howManyItems(100,2,2));
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        System.out.println(gcd(48,18));
        System.out.println(gcd(52,8));
        System.out.println(gcd(259,28));
        System.out.println(ticketSaler(70,1500));
        System.out.println(ticketSaler(24,950));
        System.out.println(ticketSaler(53,1250));
        System.out.println(tables(5,2));
        System.out.println(tables(31,20));
        System.out.println(tables(123,58));
    }

    public static float convert(int x) {
        float gallon = 3.785f;
        return x * gallon;
    }


    public static int fitCalc(int x, int y) {
        return x * y;
    }

    public static int containers(int x, int y, int z) {
        return x*20+y*50+z*100;
    }

    public static String triangleType(int x, int y, int z) {
        if (x==y & y==z & x+y>z & z+y>x && x+z>y) {
            System.out.println("isosceles");
            return "isosceles";
        } else if (x!=y & y==z & x+y>z & z+y>x & x+z>y | x!=z & y==z & x+y>z & z+y>x & x+z>y | x==z & y!=z & x+y>z & z+y>x & x+z>y) {
            System.out.println("equilateral");
            return "equilateral";
        } else if (x!=y & y!=z) {
            System.out.println("different-sided");
            return "different-sided";
        } else {
            System.out.println("not a triangle");
            return "not a triangle";
        }
    }

    public static int ternaryEvaluation(int a, int b) {
        return (a > b) ? a : b;
    }


    public static int howManyItems(int n, float w, float h) {
        int m = (int) (n / ((w + h) * 2));
        return m;
    }

    //используем пришедшее число как размер нашего цикла, в котором умножаем на все предыдущие числа, пока не дойдём до
    public static int factorial(int x){
        int result = 1;
        for (int i = 1; i <= x; i++){
            result = result * i;
        }
        return result;
    }

    public static int gcd(int x, int y){
        while ( x != 0 &  y!=0){
            if (x>y){
               x = x%y;  //Находим остаток от деления
            } else {
                y = y%x; // Присваиваем второй переменной остаток от деления
            }
        }
        return x+y;
    }

    public static int ticketSaler(int x, int y){
        float m;
        m=x*y;
        float n;
        n= (float) (m*0.28);
        return (int) (m-n);
    }

    public static int tables(int x, int y) {
        int requiredDesks = (int) Math.ceil((double) x / 2); //Math.ceil() округляет до ближайшего целого числа вверх, но отдаёт не целочисленный тип.
        //Даже если у нас будет 34.00, все равно после использования Math.ceil мы получим 35,0
        int desksNeeded = requiredDesks - y;
        return Math.max(0, desksNeeded); //Math.max() возвращает максимальное число из двух аргументов
    }
}
