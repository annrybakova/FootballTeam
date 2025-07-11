package com.solvd.models.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.models.enums.PlayerPosition;

public class Defender extends FootballPlayer {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public Defender(String name, int skill, int price) {
        super(name, skill, price);
        setPosition(PlayerPosition.DEFENDER);
    }

    @Override
    public void beEnrolled(FootballTeam team) {
        logger.info(getPlayerName() + " joined team " + team.getFootballTeamName() + " as a " + getPlayerName());
    }

}
