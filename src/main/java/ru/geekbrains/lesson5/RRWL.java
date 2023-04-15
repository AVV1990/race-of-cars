package ru.geekbrains.lesson5;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RRWL {
    public static void main(String[] args) {

        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        new Thread(() -> {
            System.err.println("BEFORE-LOCK-1");

            try {
                rwl.readLock().lock();// захват замка  на чтение
                System.out.println("Reading-start-a");
                Thread.sleep(3000);
                System.out.println("Reading-end-a");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                rwl.readLock().unlock(); // отпуск замка  на чтение
            }
        }).start();


        new Thread(() -> {
            System.err.println("BEFORE-LOCK-1");

            try {
                rwl.readLock().lock();// захват замка  на чтение
                System.out.println("Reading-start-b");
                Thread.sleep(5000);
                System.out.println("Reading-end-b");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                rwl.readLock().unlock(); // отпуск замка  на чтение
            }
        }).start();
    }
}
