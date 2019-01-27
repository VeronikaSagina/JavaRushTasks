package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {

    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
        list.remove(1);
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 10; i++) {
            System.out.print(iterator.next());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return size() != 0;
        }

        @Override
        public T next() {
            checkForComodification();
            int i = cursor;
            if (cursor == size() - 1) {
                cursor = 0;
            } else {
                cursor = i + 1;
            }
            return Solution.this.get(lastRet = i);
        }

        @Override
        public void remove() {
            Solution.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
            expectedModCount = modCount;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
