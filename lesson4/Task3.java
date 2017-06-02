//3.    Написать класс МФУ, на котором возможны одновременная печать и сканирование документов, при этом нельзя одновременно печатать два документа
// 		или сканировать (при печати в консоль выводится сообщения "отпечатано 1, 2, 3,... страницы", при сканировании то же самое
// 		только "отсканировано...", вывод в консоль все также с периодом в 50 мс.).

package lesson4;

import java.util.concurrent.*;

// Класс МФУ
public class Task3 {
    Object printNow = new Object();
    Object scanNow = new Object();

    class Printer implements Runnable {
        String name;
        int pages;

        Printer(String name, int pages) {
            this.name = name;
            this.pages = pages;
        }

        @Override
        public void run() {
            try {
                synchronized (printNow) {
                    int currentPage = 1;
                    while (currentPage <= pages) {
                        System.out.println(name + ": отпечатана " + currentPage++ + " страница");
                        Thread.currentThread().sleep(50);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class Scanner implements Runnable {
        String name;
        int pages;

        Scanner(String name, int pages) {
            this.name = name;
            this.pages = pages;
        }

        @Override
        public void run() {
            try {
                synchronized (scanNow) {
                    int currentPage = 1;
                    while (currentPage <= pages) {
                        System.out.println(name + ": отсканирована " + currentPage++ + " страница");
                        Thread.currentThread().sleep(50);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Task3 t3 = new Task3();
            ExecutorService eS = Executors.newFixedThreadPool(2);
            eS.submit(t3.new Printer("Doc1",3));
            eS.submit(t3.new Printer("Doc3",3));
            eS.submit(t3.new Scanner("Doc2",3));
            eS.submit(t3.new Scanner("Doc4",3));
            eS.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
