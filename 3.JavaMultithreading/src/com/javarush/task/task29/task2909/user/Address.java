package com.javarush.task.task29.task2909.user;

public class Address {
    private String country;
    private String city;
    private String house;

    public Address(){

    }
    /*public Address(String country, String city, String house) {
        this.country = country;
        this.city = city;
        this.house = house;
    }*/

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
