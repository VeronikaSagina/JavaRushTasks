package com.javarush.task.task25.task2512;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        e.printStackTrace();
      /*  List<Throwable> throwableList = new ArrayList<>();
        for (; e != null; e = e.getCause()) {
            throwableList.add(e);
        }
        Collections.reverse(throwableList);
        for(Throwable thr : throwableList) {
            System.out.println(thr);
        }*/
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("DEF", new IllegalAccessException("GHI"));
        });
        thread.setUncaughtExceptionHandler(new Solution());
        thread.start();
/*
        while (true) {

        }
*/
    }
}
