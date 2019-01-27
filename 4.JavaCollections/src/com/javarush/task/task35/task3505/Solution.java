package com.javarush.task.task35.task3505;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* 
List to Map
*/
public class Solution {
    public static void main(String[] args) {
        List<ConvertableUser> users = new ArrayList<>();
        users.add(new ConvertableUser(234, "First UserLog"));
        users.add(new ConvertableUser(235, "Second UserLog"));
        users.add(new ConvertableUser(236, "Third UserLog"));

        Map<Integer, ConvertableUser> newMap = ConvertableUtil.convert(users);
        System.out.println(newMap);
        //{236=ConvertableUser{id=236, name='Third UserLog'},
        // 235=ConvertableUser{id=235, name='Second UserLog'},
        // 234=ConvertableUser{id=234, name='First UserLog'}}

        //////////////////////////////////////////////////////////

        List<ConvertableBook> books = new ArrayList<>();
        books.add(new ConvertableBook("First Book"));
        books.add(new ConvertableBook("Second Book"));
        books.add(new ConvertableBook("Third Book"));

        Map<String, ConvertableBook> bookMap = ConvertableUtil.convert(books);
        System.out.println(bookMap);
        //{Third Book=ConvertableBook{name='Third Book'},
        // First Book=ConvertableBook{name='First Book'},
        // Second Book=ConvertableBook{name='Second Book'}}
    }
}
