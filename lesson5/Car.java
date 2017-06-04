package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class Car implements Runnable {

    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch isReady;
    private Lock lockWinner;
    private CountDownLatch isFinish;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch isReady, Lock lockWinner, CountDownLatch isFinish) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.isReady = isReady;
        this.lockWinner = lockWinner;
        this.isFinish = isFinish;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            isReady.countDown();
            isReady.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        isFinish.countDown();
        if (lockWinner.tryLock()) {
            lockWinner.lock();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Победитель гонки - " + name + "!!!");
        }
    }
}

