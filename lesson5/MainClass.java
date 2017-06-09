// Домашнее задание
// Организуем гонки
// Есть набор правил:
// Все участники должны стартовать одновременно, несмотря на то что на подготовку у каждого уходит разное время
// В туннель не может заехать одновременно больше половины участников(условность)
// Попробуйте все это синхронизировать
// Как только первая машина пересекает финиш, необходимо ее объявить победителем(победитель должен быть только один, и вообще должен быть)
// Только после того, как все завершат гонку, нужно выдать объявление об окончании
// Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent

package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;
    static CountDownLatch isReady = new CountDownLatch(4);
    static CountDownLatch Started = new CountDownLatch(1);
    static CountDownLatch isFinish = new CountDownLatch(4);
    static Lock lockWinner = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), isReady, Started, lockWinner, isFinish);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            isReady.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            Started.countDown();
            isFinish.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}