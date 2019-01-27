package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        AtomicInteger integer = new AtomicInteger(1);
        //int integer = 1;
        try {
            while (true) {
                map.put(String.valueOf(integer), "Some text for " + integer);
                integer.incrementAndGet();
                //integer++;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
           System.out.println(Thread.currentThread().getName() + " thread was terminated");
        }
    }
}
