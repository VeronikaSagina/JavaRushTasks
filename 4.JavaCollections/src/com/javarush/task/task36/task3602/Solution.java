package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
        for (Class<?> clazz : declaredClasses) {
            if (!Modifier.isStatic(clazz.getModifiers()) || !Modifier.isPrivate(clazz.getModifiers())) {
                continue;
            }
            if (List.class.isAssignableFrom(clazz)) {
                try {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List list = (List) constructor.newInstance();
                    list.get(0);
                } catch (IndexOutOfBoundsException e) {
                    return clazz;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    //do nothing
                }
            }
        }
        return null;
    }
}