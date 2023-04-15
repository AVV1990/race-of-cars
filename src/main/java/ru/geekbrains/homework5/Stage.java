package ru.geekbrains.homework5;

import ru.geekbrains.homework5.Car;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}


