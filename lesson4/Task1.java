//1.  Создать три потока, каждый из которых выводит определенную букву(A, B и C) 5 раз, порядок должен быть именно ABСABСABС.
//    Используйте wait/notify/notifyAll.

package lesson4;

public class Task1 {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Task1 w = new Task1();
        new Thread(() -> {
            w.printLetter('A', 'B');
        }).start();
        new Thread(() -> {
            w.printLetter('B', 'C');
        }).start();
        new Thread(() -> {
            w.printLetter('C', 'A');
        }).start();
    }

    public void printLetter(char current, char next) {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != current)
                        mon.wait();
                    System.out.print(current);
                    currentLetter = next;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
