package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable factoryMethodForException(Object value) {
        if (value instanceof ExceptionDBMessage) {
            if (value == ExceptionDBMessage.NOT_ENOUGH_CONNECTIONS) {
                return new RuntimeException("Not enough connections");
            }
            if (value == ExceptionDBMessage.RESULT_HAS_NOT_GOTTEN_BECAUSE_OF_TIMEOUT) {
                return new RuntimeException("Result has not gotten because of timeout");
            }
        }
        if (value instanceof ExceptionApplicationMessage) {
            if (value == ExceptionApplicationMessage.SOCKET_IS_CLOSED) {
                return new Exception("Socket is closed");
            }
            if (value == ExceptionApplicationMessage.UNHANDLED_EXCEPTION) {
                return new Exception("Unhandled exception");
            }
        }
        if (value instanceof ExceptionUserMessage) {
            if (value == ExceptionUserMessage.USER_DOES_NOT_EXIST) {
                return new Error("User does not exist");
            }
            if (value == ExceptionUserMessage.USER_DOES_NOT_HAVE_PERMISSIONS) {
                return new Error("User does not have permissions");
            }
        }
        return new IllegalArgumentException();
    }
}
