package com.javarush.task.task24.task2412;

import java.util.*;
import java.util.stream.Collectors;

public class Person {
    int age;
    String name;
    Sex sex;

    public Person(int age, String name, Sex sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    enum Sex {
        FEMALE, MALE
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person(20, "James", Sex.MALE),
                new Person(20, "James", Sex.FEMALE),
                new Person(20, "Eric", Sex.MALE),
                new Person(20, "Jenifer", Sex.FEMALE),
                new Person(20, "Джон", Sex.MALE),
                new Person(18, "Benedict", Sex.MALE)
        ));

        people.sort(Comparator.comparingInt(Person::getAge)
                .thenComparing(Person::getSex, Comparator.reverseOrder())
                .thenComparing(Person::getName)
        );

//        people.forEach(System.out::println);

        final Map<Integer, Map<Sex, Map<String, List<Person>>>> collect = people.stream().collect(
                Collectors.groupingBy(Person::getAge, TreeMap::new,
                        Collectors.groupingBy(Person::getSex, TreeMap::new,
                                Collectors.groupingBy(Person::getName))));
        collect.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(map -> map.values().stream())
                .forEach(System.out::println);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
