package com.javarush.task.task39.task3913.tests;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParserTest {
    /*    public static void main(String[] args) {
            String user = "get ip for user = \"Eduard Petrovich Morozko\"";
            String value = user.substring(user.indexOf("\"") +1, user.lastIndexOf("\""));
            String[] arr = user.replace("=", "").split("\\s+");
            System.out.println(value);
            System.out.println(Arrays.toString(arr));

            List<String> listRequest = new ArrayList<>();
            for (char i = user.charAt(0); i != '"'; i++) {

            }
        }*/
    public static void main(String[] args) {
        String example = "get ip for user = \"Eduard Petrovich Morozko\"" +
                " and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
       System.out.println(parse(example));
    }

    private static List<String> parse(String sValue) {
        if (!sValue.contains("\"")) {
            return Stream.of(sValue.split(" "))
                    .collect(Collectors.toList());
        }
        String line1 = sValue.substring(0, sValue.indexOf('"')).replace("=", "");
        List<String> result = Stream.of(line1.split(" ")).collect(Collectors.toList());
        Queue<Integer> fifo = new LinkedList<>();
        for (int i = 0; i < sValue.length(); i++) {
            if (sValue.charAt(i) == '\"') {
                if (fifo.isEmpty()) {
                    fifo.add(i);
                } else {
                    int start = fifo.remove();
                    result.add(sValue.substring(start + 1, i));
                }
            }
        }
        return result;
    }
}