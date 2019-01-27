package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int count = 0;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            List<V> list = entry.getValue();
            count += list.size();
        }
        return count;
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>(Collections.singletonList(value)));
            return null;
        }
        List<V> vs = map.get(key);
        if (vs.size() == 0) {
            map.put(key, new ArrayList<>(Collections.singletonList(value)));
            return null;
        }
        if (vs.size() < repeatCount) {
            V lastElement = vs.get(vs.size() - 1);
            vs.add(value);
            map.put(key, vs);
            return lastElement;
        }
        if (vs.size() == repeatCount) {
            V lastElement = vs.get(vs.size() - 1);
            vs.remove(0);
            vs.add(value);
            map.put(key, vs);
            return lastElement;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        if (!map.containsKey(key)) {
            return null;
        }
        List<V> vs = map.get(key);
        V element = vs.get(0);
        vs.remove(0);
        if (vs.size() == 0) {
            map.remove(key);
        } else {
            map.put((K) key, vs);
        }
        return element;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            List<V> valList = entry.getValue();
            list.addAll(valList);
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            List<V> valList = entry.getValue();
            if (valList.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}