package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution solution2 = new Solution();
        Solution solution1 = new Solution();
        InnerClass innerClass1 = solution1.new InnerClass();
        InnerClass innerClass2 = solution1.new InnerClass();
        InnerClass innerClass3 = solution2.new InnerClass();
        InnerClass innerClass4 = solution2.new InnerClass();
        solution2.innerClasses[0] = innerClass1;
        solution2.innerClasses[1] = innerClass2;
        solution1.innerClasses[0] = innerClass3;
        solution1.innerClasses[1] = innerClass4;
        return new Solution[]{solution1, solution2};
    }

    public static void main(String[] args) {

    }
}
