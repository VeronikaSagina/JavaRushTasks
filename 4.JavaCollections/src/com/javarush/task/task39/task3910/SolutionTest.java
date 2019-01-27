package com.javarush.task.task39.task3910;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void aVoidwe(){

    }

    @Test
    public void isPowerOfThree() {
        Assert.assertTrue(Solution.isPowerOfThree(1));
        Assert.assertTrue(Solution.isPowerOfThree(3));
        Assert.assertTrue(Solution.isPowerOfThree(9));
        Assert.assertTrue(Solution.isPowerOfThree(59049));
        Assert.assertTrue(Solution.isPowerOfThree(6561));
        Assert.assertFalse(Solution.isPowerOfThree(48));
        Assert.assertFalse(Solution.isPowerOfThree(0));
        Assert.assertFalse(Solution.isPowerOfThree(-1));
        Assert.assertFalse(Solution.isPowerOfThree(2));
        Assert.assertFalse(Solution.isPowerOfThree(6));
        Assert.assertFalse(Solution.isPowerOfThree(198));
        Assert.assertFalse(Solution.isPowerOfThree(30));
    }
}