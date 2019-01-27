package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        Throwable exception = ExceptionFactory.factoryMethodForException(ExceptionApplicationMessage.SOCKET_IS_CLOSED);
        Throwable exception1 = ExceptionFactory.factoryMethodForException(ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS);
        Throwable exception2 = ExceptionFactory.factoryMethodForException(ExceptionUserMessage.USER_DOES_NOT_EXIST);
        System.out.println(exception.getMessage());
        System.out.println(exception1.getMessage());
        System.out.println(exception2.getMessage());
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        System.out.println(getFactoryClass());
    }

  /*  private static String convert(String value) {
        char f = value.charAt(0);
        String result = value.substring(1).toLowerCase().replaceAll("_", " ");
        return f + result;
    }*/
}