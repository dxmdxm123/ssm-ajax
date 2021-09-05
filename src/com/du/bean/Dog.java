package com.du.bean;

public class Dog {
    private int dogId;
    private String dogSex;

    @Override
    public String toString() {
        return "Dog{" +
                "dogId=" + dogId +
                ", dogSex='" + dogSex + '\'' +
                '}';
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getDogSex() {
        return dogSex;
    }

    public void setDogSex(String dogSex) {
        this.dogSex = dogSex;
    }
}
