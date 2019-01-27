package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.util.List;

/* 
Анонимность иногда так приятна!
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
        print(solution.getServers());
        print(solution.getSubscriptions());
        print(solution.getSubjects());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }
    public List<User> getUsers(){
        return new AbstractDbSelectExecutor<User>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM USER";
            }
        }.execute();
    }
    public List<Location> getLocations(){
        return new AbstractDbSelectExecutor<Location>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM LOCATION";
            }
        }.execute();
    }
    public List<Server> getServers(){
        return new AbstractDbSelectExecutor<Server>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM SERVER";
            }
        }.execute();
    }
    public List<Subject> getSubjects(){
        AbstractDbSelectExecutor<Subject> selectExecutor = new AbstractDbSelectExecutor<Subject>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM SUBJECT";
            }
        };
        return selectExecutor.execute();
    }
    public List<Subscription> getSubscriptions(){
        AbstractDbSelectExecutor<Subscription> selectExecutor = new AbstractDbSelectExecutor<Subscription>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM SUBSCRIPTION";
            }
        };
        return selectExecutor.execute();
    }
    class User extends NamedItem{

    }
}
