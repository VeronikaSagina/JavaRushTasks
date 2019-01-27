package com.javarush.task.task29.task2909.human;

import java.util.Date;

public class Student extends UniversityPerson {

    private double averageGrade;

    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    public void live() {
        learn();
    }

    public void learn() {
    }

 /*   public void incAverageGradeBy01() {
        averageGrade += 0.1;
    }

    public void incAverageGradeBy02() {
        averageGrade += 0.2;
    }
*/
    public void incAverageGrade(double delta) {
        double averageGrade = getAverageGrade();
        setAverageGrade(averageGrade += delta);
        //this.averageGrade += delta;
    }

    public void setCourse(int value) {
        course = value;
    }

    public void setAverageGrade(double value) {
        averageGrade = value;
    }

    /*public void setValue(String name, double value) {
        if (name.equals("averageGrade")) {
            averageGrade = value;
            return;
        }
        if (name.equals("course")) {
            course = (int) value;
            return;
        }
    }*/
    @Override
    public String getPosition() {
        return "Студент";
    }

    public int getCourse() {
        return course;
    }

    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

    public void setEndOfSession(Date date) {
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}