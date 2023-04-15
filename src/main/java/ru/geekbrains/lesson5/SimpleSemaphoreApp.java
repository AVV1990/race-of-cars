package ru.geekbrains.lesson5;

import java.util.concurrent.Semaphore;

public class SimpleSemaphoreApp { // используется когда нужно  увеличить производительность,  а потом стоит задача уменьшитью То есть  здесь есть ограничение
    public static void main(String[] args) {

        final Semaphore smp = new Semaphore(4); //создаем, указываем  количество "пропусков"
        Runnable limitedCall = new Runnable() {
            int count = 0;

            public void run() {
                int time = 3 + (int) (Math.random()*4.0);
                int num = count ++;
                try {
                    smp.acquire(); //  захват замка......4 3 2 1 0
                    System.out.println("Поток #" + num + "  начинает выполнять");
                    Thread.sleep(time*1000);
                    System.out.println("Поток #" + num + " завершил работу");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    smp.release();
                }
            }
        };


        for (int i = 0; i < 10; i++) {
            new Thread(limitedCall).start();
        }

    }
}
