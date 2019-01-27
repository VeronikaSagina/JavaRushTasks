package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        return students
                .stream()
                .filter(x -> averageGrade == x.getAverageGrade())
                .findFirst()
                .get();
    }

    public Student getStudentWithMaxAverageGrade() {
        double max = students.get(0).getAverageGrade();
        Student stud = students.get(0);
        for (Student student : students) {
            double order = student.getAverageGrade();
            if (order > max) {
                max = order;
                stud = student;
            }
        }
        return stud;
    }

    public Student getStudentWithMinAverageGrade() {
        double min = students.get(0).getAverageGrade();
        Student stud = students.get(0);
        for (Student student : students) {
            double order = student.getAverageGrade();
            if (order < min) {
                min = order;
                stud = student;
            }
        }
        return stud;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}