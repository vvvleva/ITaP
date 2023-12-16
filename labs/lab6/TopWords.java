import java.io.File; // импортируем класс File для работы с файлами
        import java.io.FileNotFoundException; // импортируем класс FileNotFoundException для обработки исключения, если файл не найден
        import java.util.*; // импортируем все классы из пакета java.util

public class TopWords { // объявляем класс TopWords
    public static void main(String[] args) { // объявляем метод main
        String filePath = "C:\\Users\\shkil\\OneDrive\\Рабочий стол\\Учёба\\3 семестр\\Программирование\\Лабы\\lab6\\main1.txt"; // указываем путь к файлу
        File file = new File(filePath); // создаем объект File, представляющий указанный файл
        Scanner scanner = null; // объявляем объект Scanner для чтения файла
        try { // обрабатываем исключение, если файл не найден
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // выводим информацию об исключении
        }
        Map<String, Integer> wordCountMap = new HashMap<>(); // создаем объект Map для хранения слов и их количества

        while (scanner.hasNext()) { // читаем файл по словам и добавляем их в Map
            String word = scanner.next().toLowerCase().replaceAll("[^a-zA-Z]", ""); // приводим слово к нижнему регистру и удаляем все символы, кроме букв
            if (wordCountMap.containsKey(word)) { // если слово уже есть в Map, увеличиваем количество его повторений на 1
                wordCountMap.put(word, wordCountMap.get(word) + 1);
            } else { // если слова нет в Map, добавляем его с количеством повторений равным 1
                wordCountMap.put(word, 1);
            }
        }
        scanner.close(); // закрываем Scanner

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCountMap.entrySet()); // создаем список из элементов Map

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() { // сортируем список по убыванию количества повторений
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < 10 && i < list.size(); i++) { // выводим топ-10 слов
            System.out.println(list.get(i).getKey() + " - " + list.get(i).getValue());
        }
    }
}
