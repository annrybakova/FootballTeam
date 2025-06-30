package com.solvd.models;

abstract public class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public abstract void introduceYourself();

}
