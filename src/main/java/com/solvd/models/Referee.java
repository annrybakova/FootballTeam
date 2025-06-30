package com.solvd.models;

import com.solvd.interfaces.Trainable;

abstract class Referee extends Person implements Trainable {
    protected int experience;

    public Referee(String name, int experience) {
        super(name);
        this.experience = experience;
    }

    public abstract void officiateGame(Game game);

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void train() {
        this.experience++;
        System.out.println(name + " gained experience and increased it by 1");
    }

    @Override
    public void train(double skillIncrease) {
        this.experience += skillIncrease;
        System.out.println(name + " gained experience and increased it by " + skillIncrease);
    }

    @Override
    public void introduceYourself() {
        System.out.println("Hi! I'm " + name + "and I'm a referee");
    }

}