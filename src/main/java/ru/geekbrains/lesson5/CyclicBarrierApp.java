package ru.geekbrains.lesson5;

import javax.swing.table.TableRowSorter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierApp { // циклический барьер - когда нужно засинхронизировать n-ое количество потоков, чтобы они выолнили вместе что-то одно
    public static void main(String[] args) {

        final int THREADS_COUNT = 5;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS_COUNT);

        for (int i = 0; i < THREADS_COUNT; i++) {
            int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Подготавливается " + w);
                    Thread.sleep(200 + 500 * (int) Math.random() * 10);
                    System.out.println("Готов " + w);
                    cyclicBarrier.await(); // здесь поток зависаетб сбросит до 4 3 2 1 0  и потом все это отпуксается и они  поехали одновременно
                    System.out.println("Поехал " + w);
                    Thread.sleep(200 + 500 * (int) Math.random() * 10);
                    System.out.println("Доехал " + w);
                    cyclicBarrier.await();
                    System.out.println("Гонка закончилась");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
