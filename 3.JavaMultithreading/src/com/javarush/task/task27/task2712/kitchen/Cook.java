package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Cook extends java.util.Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o /*tablet*/, Object arg /*order*/) {//отправка заказа
       // if (!((Order)arg).isEmpty()) {
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %smin", arg, ((Order)arg).getTotalCookingTime()));
        setChanged();
        notifyObservers(arg);
     //   }
    }
}
