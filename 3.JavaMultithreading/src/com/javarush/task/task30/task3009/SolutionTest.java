package com.javarush.task.task30.task3009;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SolutionTest {

    @Test
    public void isPalindrome() {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,4));
        Assert.assertFalse(Solution.isPalindrome(a));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1,0,0,1));
        Assert.assertTrue(Solution.isPalindrome(b));
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(1,2,2,1));
        Assert.assertTrue(Solution.isPalindrome(c));
        ArrayList<Integer> d = new ArrayList<>(Arrays.asList(1,2,5,4));
        Assert.assertFalse(Solution.isPalindrome(d));
        ArrayList<Integer> e = new ArrayList<>(Arrays.asList(4,2,3,4));
        Assert.assertFalse(Solution.isPalindrome(e));
    }
}