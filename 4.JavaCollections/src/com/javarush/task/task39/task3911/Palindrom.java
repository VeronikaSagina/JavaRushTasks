package com.javarush.task.task39.task3911;

public class Palindrom {
    public static void main(String[] args) {
        System.out.println(recursion("топот"));
        String s = "qweertyhgftyhhfgfdd";
        String r = new StringBuilder(s).reverse().toString();
        System.out.println(recursion(s + r));
    }
    private static boolean recursion(String string){
        if (string.length() <= 1){
            return true;
        }
        if (string.charAt(0) != string.charAt(string.length() -1)){
           return false;
        }
        return recursion(string.substring(1, string.length() - 1));
    }
}
