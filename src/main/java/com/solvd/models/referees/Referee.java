package com.solvd.models.referees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.interfaces.Trainable;
import com.solvd.models.enums.RefereePosition;
import com.solvd.models.game.Game;
import com.solvd.models.utils.Person;

abstract class Referee extends Person implements Trainable {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    protected int experience;
    private RefereePosition position;

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
        logger.info(name + " gained experience and increased it by 1");
    }

    @Override
    public void train(double skillIncrease) {
        this.experience += skillIncrease;
        logger.info(name + " gained experience and increased it by " + skillIncrease);
    }

    @Override
    public void introduceYourself() {
        logger.info("Hi! I'm " + name + "and I'm a referee");
    }


    public RefereePosition getPosition() {
        return position;
    }

    public void setPosition(RefereePosition position) {
        this.position = position;
    }

}