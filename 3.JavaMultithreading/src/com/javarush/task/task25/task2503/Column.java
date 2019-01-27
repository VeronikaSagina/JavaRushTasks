package com.javarush.task.task25.task2503;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    public static int[] getRealOrder() {
        return realOrder;
    }

    public static void setRealOrder(int[] realOrder) {
        Column.realOrder = realOrder;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException
                                ("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке
     * (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        class ColumnAndOrder{
            private Column column;
            private int order;

            public ColumnAndOrder(Column column, int order) {
                this.column = column;
                this.order = order;
            }

            public Column getColumn() {
                return column;
            }

            public int getOrder() {
                return order;
            }
        }

        List<ColumnAndOrder> columnAndOrders = new ArrayList<>();

        for (int i = 0; i < realOrder.length; i++) {
            columnAndOrders.add(new ColumnAndOrder(values()[i], realOrder[i]));
        }

        result.addAll(columnAndOrders.stream()
                .filter(columnAndOrder -> columnAndOrder.getOrder() != -1)
                .sorted(Comparator.comparing(ColumnAndOrder::getOrder))
                .map(ColumnAndOrder::getColumn)
                .collect(Collectors.toList()));
        return result;
    }

    @Override
    public String getColumnName() { //полное имя колонки
        return columnName;
    }

    /**
     * Возвращает true, если колонка видимая, иначе false
     */
    @Override
    public boolean isShown() {
        return realOrder[ordinal()] != -1;
    }

    /**
     * Скрывает колонку - маркирует колонку -1 в массиве realOrder.
     * Сдвигает индексы отображаемых колонок, которые идут после скрытой
     */
    @Override
    public void hide() {
        int index = realOrder[ordinal()];
        if (index == -1){
            return;
        }
        realOrder[ordinal()] = -1;
        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] != -1) {
                if (realOrder[i] > index) {
                    realOrder[i] -= 1;
                }
            }
        }
    }
}
