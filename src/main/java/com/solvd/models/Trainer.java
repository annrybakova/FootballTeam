package com.solvd.models;

import com.solvd.App;
import com.solvd.interfaces.Coachable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trainer implements Coachable {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private String name;
    private int experienceLevel;

    public Trainer(String name, int experienceLevel) {
        this.name = name;
        this.experienceLevel = experienceLevel;
    }

    @Override
    public void coachTeam(FootballTeam team) {
        logger.info("Trainer " + name + " is coaching team " + team.getFootballTeamName());
        team.updatePlayersSkill(experienceLevel / 2);
    }

    @Override
    public void trainPlayer(FootballPlayer player) {
        logger.info("Trainer " + name + " is training player " + player.getPlayerName());
        player.train(experienceLevel * 0.1);
    }

    @Override
    public String toString() {
        return "Trainer{name='" + name + "', experienceLevel=" + experienceLevel + "}";
    }

}
