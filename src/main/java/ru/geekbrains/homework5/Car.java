package ru.geekbrains.homework5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;

    private Race race;
    private int speed;
    private String name;
    private final CyclicBarrier barrier;

    private static boolean winnerFound = false;


    public String getName() {
        return name;
    }

        public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed,CyclicBarrier barrier) {
        this.race = race;
        this.speed = speed;
        this.barrier =barrier;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await(); //  ждем, когда все участники подготовятся
            barrier.await();
//            signal.await(); //  ожидаем сигнал на старте
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        try {
            checkWinner (this);
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


    private static synchronized void checkWinner (Car car) { // синхронизация стоит,чтобы не могло войти больше двух игроков в этот метод
        if (!winnerFound){ // если победитель не нашелся, то
            System.out.println (car.name +  " - WIN"); // эта машина победитель
            winnerFound = true;
        }
    }


}


