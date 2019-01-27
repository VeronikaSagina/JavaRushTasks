package com.javarush.task.task27.task2707;

import java.util.concurrent.atomic.AtomicBoolean;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread test = new Thread(() -> solution.someMethodWithSynchronizedBlocks(o1, o2));

        AtomicBoolean o1Locked = new AtomicBoolean(false);
        final Runnable o1Runnable = () -> {
            try {
                synchronized (o1) {
                    o1Locked.set(true);
                    while (!Thread.interrupted()) {
                        Thread.sleep(1);
                    }
                }
            } catch (InterruptedException e) {
                //do nothing
            }
        };
        Thread blockO1 = new Thread(o1Runnable);
        AtomicBoolean o2Locked = new AtomicBoolean(false);
        Thread blockO2 = new Thread(() -> {
            try {
                synchronized (o2) {
                    o2Locked.set(true);
                    while (!Thread.interrupted()) {
                        Thread.sleep(1);
                    }
                }
            } catch (InterruptedException e) {
                //do nothing
            }
        });
        try {
            blockO1.start();
            while (!o1Locked.get()) {
                //do nothing
            }
            test.start();
            while (test.getState() != Thread.State.BLOCKED) {
                //do nothing
            }

            blockO2.start();
            while (!o2Locked.get() && blockO2.getState() != Thread.State.BLOCKED) {
                // do nothing
            }
            return o2Locked.get();
        } finally {
            blockO1.interrupt();
            blockO2.interrupt();
        }
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
