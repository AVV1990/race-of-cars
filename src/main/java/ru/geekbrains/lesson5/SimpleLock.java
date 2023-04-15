package ru.geekbrains.lesson5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLock {
    public static void main(String[] args) {

        final Lock lock = new ReentrantLock(); // Lock -  это интерфейс, a ReentrantLock реализация этого интерфейса
        // lock  - это некий монитор, который похож на синхронизацию

        new Thread(() -> {
            System.err.println("BEFORE-LOCK-1");

            try {
                lock.lock();// захват замка
                System.err.println("GET-LOCK-1");
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.err.println("END-1");
                lock.unlock(); // отпуск замка
            }
        }).start();

        new Thread(() -> {
            System.err.println("BEFORE-LOCK-2");

            try {
                lock.lock();
                System.err.println("GET-LOCK-2");
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.err.println("END-2");
                lock.unlock();
            }
        }).start();

    }
}
