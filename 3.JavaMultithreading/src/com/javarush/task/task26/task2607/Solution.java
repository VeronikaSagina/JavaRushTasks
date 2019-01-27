package com.javarush.task.task26.task2607;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
Вежливость - это искусственно созданное хорошее настроение
*/
public class Solution {
    public static class IntegerHolder{
       private int value;
        ReadWriteLock lock = new ReentrantReadWriteLock();
        public int get() {
            lock.readLock().lock();
            try {
                return value;
            }finally {
                lock.readLock().unlock();
            }
        }

        public synchronized void set(int value) {
            lock.writeLock().lock();
            this.value = value;
            lock.writeLock().unlock();
        }
    }
    public static void main(String[] args) {
    }
}
