package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public static void main(String[] args) throws Exception {
        AmigoSet initialAmigoSet = new AmigoSet<>();

        for (int i = 0; i < 10; i++) {
            initialAmigoSet.add(i);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(initialAmigoSet);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        AmigoSet loadedAmigoSet = (AmigoSet) objectInputStream.readObject();

        System.out.println(initialAmigoSet.size() + " " + loadedAmigoSet.size());
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int) Math.ceil(collection.size() / .75f));
        map = new HashMap<>(capacity);
        for (E e : collection) {
            map.put(e, PRESENT);
        }
        addAll(collection);
    }

    //serialize
    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        s.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        s.writeInt(map.size());
        for (E e : map.keySet())
            s.writeObject(e);
    }

    //deserialize
    private void readObject(java.io.ObjectInputStream s) throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        int capacity = s.readInt();
        if (capacity < 0) {
            throw new InvalidObjectException("Illegal capacity: " +
                    capacity);
        }
        float loadFactor = s.readFloat();
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new InvalidObjectException("Illegal load factor: " +
                    loadFactor);
        }
        int size = s.readInt();
        if (size < 0) {
            throw new InvalidObjectException("Illegal size: " +
                    size);
        }
        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E e = (E) s.readObject();
            add(e);
        }
    }

    @Override
    public Object clone() throws InternalError {
        try {
            AmigoSet newSet = (AmigoSet) super.clone();
            newSet.map = (HashMap) map.clone();
            return newSet;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }


    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (map.put(e, PRESENT) != null) {
                modified = true;
            }
        return modified;
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }
}
