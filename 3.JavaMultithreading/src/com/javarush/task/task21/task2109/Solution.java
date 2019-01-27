package com.javarush.task.task21.task2109;

import java.util.Objects;

/*
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof A)) return false;
            A a = (A) o;
            return i == a.i &&
                    j == a.j;
        }

        @Override
        public int hashCode() {

            return Objects.hash(i, j);
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public A clone() throws CloneNotSupportedException {
            return (A)super.clone();
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof B)) return false;
            if (!super.equals(o)) return false;
            B b = (B) o;
            return Objects.equals(name, b.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(super.hashCode(), name);
        }

        public String getName() {
            return name;
        }

        @Override
        public B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public C clone() throws CloneNotSupportedException {
            return new C(this.getI(), this.getJ(), this.getName());
        }
    }

    public static void main(String[] args) {

    }
}
