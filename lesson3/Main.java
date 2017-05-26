//1.  Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
//2.  Последовательно сшить 5 файлов в один (файлы также ~100 байт).
//3.  Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb),
//    вводим страницу, программа выводит ее в консоль (за страницу можно принять 1800 символов).
//    Время чтения файла должно находится в разумных пределах (программа не должна загружаться дольше 10 секунд),
//    ну и чтение тоже не должно занимать >5 секунд.

package lesson3;

import java.io.*;

public class Main {
    // task 1
    static void task1() {
        try (FileInputStream fis = new FileInputStream("file1")) {
            byte b[] = new byte[50];
            fis.read(b);
            for (int i = 0; i < b.length; i++)
                System.out.println(b[i]);
        }
        catch (Exception ex) {}
    }

    // task 2
    static void task2() {
        try (FileOutputStream fos = new FileOutputStream("file_out", true)) {
            String filename;
            for (int i = 1; i <= 5; i++) {
                filename = "file_in_" + i;
                FileInputStream fis = new FileInputStream(filename);
                byte[] b = new byte[100];
                fis.read(b);
                fos.write(b);
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // task 3
    static void task3(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("tolstoy.txt", "r")) {
            raf.seek(1800 * Integer.parseInt(args[0]));
            byte[] b = new byte[1800];
            raf.read(b);
            for (int i = 0; i < b.length; i++) {
                System.out.print((char)b[i]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}