package com.solvd.models.team;

import com.solvd.exceptions.InsufficientFundsException;
import com.solvd.interfaces.Trackable;
import com.solvd.interfaces.Trainable;
import com.solvd.models.utils.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Manager extends Person implements Trackable {
    private static final Logger logger = LoggerFactory.getLogger(Manager.class);
    private double money;

    public Manager(String name, double money) {
        super(name);
        this.money = money;
    }

    public void buyFootballPlayer(FootballPlayer player, FootballTeam team) throws InsufficientFundsException {
        if (money >= player.getPrice()) {
            money -= player.getPrice();
            team.addFootballPlayer(player);
            // return true;
        } else {
            // System.out.println("Not enough money to buy " +
            // player.getPlayerName().toString());
            // return false;
            throw new InsufficientFundsException(
                    "Manager " + name + " does not have enough money to buy " + player.getPlayerName());
        }
    }

    public void trainPlayer(Trainable player, double skillIncrease) {
        player.train(skillIncrease);
        logger.info("Manager " + name + " trained a player" + player);
    }

    public void earnMoney(double score) {
        money += score;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void introduceYourself() {
        logger.info("Hi! I'm " + name + "and I'm a manager");
    }

    @Override
    public void displayStats() {
        logger.info("Manager Name: " + name);
        logger.info("Available Funds: $" + money);
    }

}
