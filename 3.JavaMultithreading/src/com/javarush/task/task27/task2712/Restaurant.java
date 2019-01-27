package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(5);
       // final Order order = tablet.createOrder();
        final Cook amigo = new Cook("Amigo");
        tablet.addObserver(amigo);

      //  if (!order.isEmpty()) {
           // amigo.update(tablet, order);
       // }
        final Waiter waiter = new Waiter();
        amigo.addObserver(waiter);
       // if (!order.isEmpty()) {
      //      waiter.update(amigo, order);
      //  }
        tablet.createOrder();
    }
}
/*
class RecursionExample {

    private int values[];

    RecursionExample(int i) {
        values = new int[i];
    }
    void printArray(int i) {

        if(i == 0) {
            return;
        } else {
            System.out.println("[" + (i - 1) + "] " + values[i - 1]);
            printArray(i - 1);
            System.out.println("[" + (i - 1) + "] " + values[i - 1]);
        }

    }
}*/
