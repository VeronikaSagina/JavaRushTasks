package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
//        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(writer, one);
        String res = writer.toString();
        System.out.println(res);
        StringReader reader = new StringReader(res);
        //noinspection unchecked
        return objectMapper.readValue(reader, resultClassObject);
    }

    public static class First {
        public int i;
        public String name;
    }

    public static class Second {
        @JsonCreator
        public Second(@JsonProperty("i") int i, @JsonProperty("name") String name) {
            this.i = i;
            this.name = name;
        }

        public int i;
        public String name;
    }
}
