package ru.geekbrains.homework5;

import ru.geekbrains.homework5.Car;
import ru.geekbrains.homework5.Stage;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore smp;
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        smp = new Semaphore(MainClass.CARS_COUNT/2);// создаем, указываем  количество "пропуcков" через тоннель
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();  // захват....только половина машин могут заехать в туннель
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release(); // отпускает,

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

