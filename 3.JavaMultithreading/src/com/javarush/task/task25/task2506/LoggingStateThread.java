package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {

    private Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(state);
        while (thread.getState() != State.TERMINATED) {
            if (thread.getState() != state) {
                System.out.println(thread.getState());
                state = thread.getState();
            }
        }
        System.out.println(thread.getState());
    }
}