package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) throws IllegalArgumentException {
        if (i < 1 || i > 4){
            throw new IllegalArgumentException();
        }
        Operation[] values = Operation.values();
        return values[i];
    }
}
