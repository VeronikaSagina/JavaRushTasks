package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        V value = cache.get(key);
        if (value == null){
            V value1 = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, value1);
            return value1;
        }
        return value;
    }

    public boolean put(V obj)  {
        try {
            Class<V> classObj = (Class<V>) obj.getClass();
            Method method = classObj.getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);
            return true;
        }catch (Exception e){
            // do nothing
        }
        return false;
    }

    public int size() {
        return cache.size();
    }
}
