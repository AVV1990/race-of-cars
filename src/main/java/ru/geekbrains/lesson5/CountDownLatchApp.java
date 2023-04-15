package ru.geekbrains.lesson5;

import java.io.File;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchApp { // используют когда нужно выполнить какую -то часть задач n - ому количеству потоков ,  необязательно всю задачу
    public static void main(String[] args) {

        final int THREADS_COUNT = 6; //  6 щелчков- потоков

        final CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

        System.out.println("Начинаем");

        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {

                try {
                    Thread.sleep(200 * w + (int) (500*Math.random()));
                    System.out.println("поток # " + w + " готов");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Работа завершена");


    }
}
