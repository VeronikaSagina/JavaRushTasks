package com.javarush.task.task25.task2503;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ColumnTest {

    @org.junit.Test
    public void hide() {
        Column.setRealOrder(new int[]{3,2,1,0});
        Column.values()[3].hide();
        Assert.assertArrayEquals(new int[]{2,1,0,-1}, Column.getRealOrder());

        Column.setRealOrder(new int[]{2,-1,1,0});
        Column.values()[0].hide();
        Assert.assertArrayEquals(new int[]{-1,-1,1,0}, Column.getRealOrder());

        Column.setRealOrder(new int[]{0,1,2,3});
        Column.values()[2].hide();
        Assert.assertArrayEquals(new int[]{0,1,-1,2}, Column.getRealOrder());

        Column.setRealOrder(new int[]{0,1,-1,2});
        Column.values()[2].hide();
        Assert.assertArrayEquals(new int[]{0,1,-1,2}, Column.getRealOrder());
    }
    @org.junit.Test
    public void test(){
        Column.configureColumns(Column.Amount, Column.AccountNumber, Column.BankName);
        List<Column> expected = new LinkedList<>(Arrays.asList(Column.Amount, Column.AccountNumber, Column.BankName));
        expected.forEach(System.out::println);
        Assert.assertEquals(expected,  Column.getVisibleColumns());

        Column.configureColumns( Column.AccountNumber, Column.Amount, Column.Customer, Column.BankName);
        List<Column> expected1 = new LinkedList<>(Arrays.asList(Column.AccountNumber, Column.Amount, Column.Customer, Column.BankName));
        expected.forEach(System.out::println);
        Assert.assertEquals(expected1,  Column.getVisibleColumns());
    }
}