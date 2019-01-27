
package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder s = new StringBuilder();
        while (reader.ready()){
            s.append(reader.readLine());
        }
        reader.close();
        StringReader stringReader = new StringReader(s.toString());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stringReader, clazz);
/*
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName), clazz);
*/
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        Player player = new Player();
        player.age = 20;
        player.name = "Danila";
        ObjectMapper mapper = new ObjectMapper();
        String pathname = "D:\\temp\\player.txt";
        mapper.writeValue(new File(pathname), player);
        System.out.println(convertFromJsonToNormal(pathname, Player.class));

    }

    @JsonAutoDetect
    private static class Player {
        public String name;
        public int age;

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
