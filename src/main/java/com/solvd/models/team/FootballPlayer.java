package com.solvd.models.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.interfaces.Marketable;
import com.solvd.interfaces.Trackable;
import com.solvd.interfaces.Trainable;
import com.solvd.models.enums.PlayerPosition;
import com.solvd.models.enums.RefereePosition;
import com.solvd.models.utils.Person;

public abstract class FootballPlayer extends Person implements Trainable, Marketable, Trackable {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    protected double skill;
    protected int price;
    private PlayerPosition position;

    public FootballPlayer(String name, double skill, int price) {
        super(name);
        this.skill = skill;
        this.price = price;
    }

    public abstract void beEnrolled(FootballTeam team);

    public double getPlayerSkill() {
        // logger.info("Football Player " + name + " has skill of " + skill);
        return skill;
    }

    public void setPlayerSkill(double skill) {
        this.skill = skill;
    }

    public void updatePlayerSkill(double bonus) {
        this.skill += bonus;
        logger.info("Football Player " + name + " increased skill by " + bonus);
    }

    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
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
        logger.info("Hi! I'm " + name + "and I'm a football player");
    }

    @Override
    public void train(double skillIncrease) {
        this.skill += skillIncrease;
        logger.info(name + " trained and increased skill by " + skillIncrease);
    }

    @Override
    public void displayStats() {
        logger.info("Player Name: " + name);
        logger.info("Skill Level: " + skill);
        logger.info("Price: $" + price);
    }
}
