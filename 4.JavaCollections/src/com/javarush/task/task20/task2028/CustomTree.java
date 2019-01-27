package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    int size = 0;

    public CustomTree() {
       root = new Entry<>("w");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        Entry<String> x = root;
        if (x == null) {
            root = new Entry<>(s);
            size = 1;
            root.parent = null;
            return true;
        }
        Entry<String> parent = null;// родитель текущей вершины
        Entry<String> current = root;//текущая вершина
        while (current != null) {
            parent = current;
            if (s.compareTo(current.elementName) > 0) {
                current = current.rightChild;
            } else if (s.compareTo(current.elementName) < 0) {
                current = current.leftChild;
            } else {
                return false;
            }
        }
        size++;
        current = new Entry<>(s);
        current.parent = parent;
        if (s.compareTo(parent.elementName) > 0) {
            parent.rightChild = current;
        } else {
            parent.leftChild = current;
        }
        return true;
    }

    String getParent(String s) {
        CustomTree tree = new CustomTree();
        final Entry<String> stringEntry = tree.find(s);
        if (stringEntry == null){
            return null;
        }
        return stringEntry.parent == null ? null : stringEntry.parent.elementName;
    }

    private Entry<String> find(String s){
        Entry<String> current = root;
        while (current != null){
            if (s.compareTo(current.elementName) == 0){
                return current;
            }else if (s.compareTo(current.elementName) > 0){
                current = current.rightChild;
            }else {
                current = current.leftChild;
            }
        }
        return null;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;


        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (rightChild != null) {
                availableToAddRightChildren = false;
            }
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
