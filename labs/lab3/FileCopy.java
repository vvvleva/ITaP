import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        File sourceFile = new File("source.txt"); // создание файла источника
        File destFile = new File("recipient.txt"); // создание файла получателя

        // Копирование данных из одного файла в другой
        try (FileInputStream fis = new FileInputStream(sourceFile); // Создаем объект ввода
             FileOutputStream fos = new FileOutputStream(destFile)) { //Создаем объект вывода
            byte[] buffer = new byte[1024]; // создаем массив buffer размером 1024 байта для чтения данных из исходного файла
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) { // Цикл while выполняется пока возвращаемое значение метода read() не станет равным -1, что означает достижение конца файла
                fos.write(buffer, 0, bytesRead);  // чтение данных из исходного файла в буфер и их запись в целевой файл с использованием метода write()
                // Функция write() принимает 3 параметра: массив данных buffer, смещение в массиве (в данном случае 0) и кол-во байтов, которое нужно записать (в данном случае bytesRead)
            }
            System.out.println("Файл успешно скопирован!");
        } catch (FileNotFoundException e) {  // Обработка исключения типа ExceptionType1
            System.err.println("Один из файлов не существует");
            e.printStackTrace(); // метод сообщает что произошло и в каком месте кода
        } catch (IOException e) {// IOException — ошибка при вводе-выводе данных (Input-Output). Обработка исключения типа ExceptionType2
            System.err.println("Произошла ошибка при копировании файла");
            e.printStackTrace();
            // "e" в команде используется как обозначение переменной типа Exception. При возникновении исключения оно будет присвоено
            // переменной "e". Затем, используя данную переменную, можно обработать исключение или выполнить необходимые действия при его возникновении
        }
    }
}
