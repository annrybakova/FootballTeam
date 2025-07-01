package com.solvd.models.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;

public class Forward extends FootballPlayer {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String type = "Forward";

    public Forward(String name, int skill, int price) {
        super(name, skill, price);
    }

    @Override
    public void beEnrolled(FootballTeam team) {
        logger.info(type + getPlayerName() + " joined team " + team.getFootballTeamName());
    }
}
