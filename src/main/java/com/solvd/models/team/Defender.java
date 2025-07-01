package com.solvd.models.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;

public class Defender extends FootballPlayer {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String type = "Defender";

    public Defender(String name, int skill, int price) {
        super(name, skill, price);
    }

    @Override
    public void beEnrolled(FootballTeam team) {
        logger.info(getPlayerName() + " joined team " + team.getFootballTeamName() + " as a " + type);
    }

}
