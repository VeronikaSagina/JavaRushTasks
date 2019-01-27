package com.javarush.task.task39.task3909;

/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("am", "mak"));
        System.out.println(isOneEditAway("aline", "line"));
        System.out.println(isOneEditAway("line", "liene"));
        System.out.println(isOneEditAway("line", "linse"));
        System.out.println(isOneEditAway("line", "linsess"));
        System.out.println(isOneEditAway("01", "102"));
        System.out.println(isOneEditAway("1032", "102"));
        System.out.println(isOneEditAway("123", "1023"));
        System.out.println(isOneEditAway("1236789765", "1596325023"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        String longest = first.length() > second.length() ? first : second;
        //noinspection StringEquality
        String shortest = first == longest ? second : first;
        if(longest.length() - shortest.length() > 1) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < shortest.length(); i++) {
            if (shortest.charAt(i) != longest.charAt(i)){
                if (count > 0){
                    return false;
                }
                count++;
                if (longest.length() == shortest.length()) {
                    continue;
                }
                longest = new StringBuilder(longest).deleteCharAt(i).toString();
                i--;
            }
        }
        return true;
    }
}/*false
true
true
true
false
false
true
true
*/
