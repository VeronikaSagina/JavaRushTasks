package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        String s = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
        String ss = "120.120.120.122\tAmigo\t29.2.2028 5:4:7\tSOLVE_TASK 18\tOK";
        String vasya = "192.168.100.2\tVasya Pupkin\t30.08.2012 16:08:40\tDONE_TASK 15\tOK";
        LogParser2 logParser = new LogParser2(Paths.get("C:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
        //  System.out.println(logParser.getUniqueIPs(null, null));
        //  System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
     /*   System.out.println(logParser.execute("get ip for user = \"Vasya\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));*/
     System.out.println(logParser.execute("get event for ip = \"192.168.100.2\""));
        System.out.println(logParser.execute("get event for ip = \"192.168.100.2\"" +
                " and date between \"30.08.2012 16:08:13\" and \"19.03.2016 00:00:00\""));

        Specification specification = new Specification();
        specification.setReturning("ip");
        specification.add("user", "Vasya Pupkin");
        specification.add("event", Event.SOLVE_TASK);
        Set<Object> set = specification.apply(logParser.getLogs());
        System.out.println(set);
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));

    }
}