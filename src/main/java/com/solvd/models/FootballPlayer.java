package com.solvd.models;

import com.solvd.interfaces.Marketable;
import com.solvd.interfaces.Trackable;
import com.solvd.interfaces.Trainable;

public abstract class FootballPlayer extends Person implements Trainable, Marketable, Trackable {
    protected double skill;
    protected int price;

    public FootballPlayer(String name, double skill, int price) {
        super(name);
        this.skill = skill;
        this.price = price;
    }

    public abstract void beEnrolled(FootballTeam team);

    public double getPlayerSkill() {
        System.out.println("Football Player " + name + " has skill of " + skill);
        return skill;
    }

    public void setPlayerSkill(double skill) {
        this.skill = skill;
    }

    public void updatePlayerSkill(double bonus) {
        this.skill += bonus;
        System.out.println("Football Player " + name + " increased skill by " + bonus);
    }

    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void introduceYourself() {
        System.out.println("Hi! I'm " + name + "and I'm a football player");
    }

    @Override
    public void train(double skillIncrease) {
        this.skill += skillIncrease;
        System.out.println(name + " trained and increased skill by " + skillIncrease);
    }

    @Override
    public void displayStats() {
        System.out.println("Player Name: " + name);
        System.out.println("Skill Level: " + skill);
        System.out.println("Price: $" + price);
    }
}
