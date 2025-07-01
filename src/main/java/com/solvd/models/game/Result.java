package com.solvd.models.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.models.team.FootballTeam;

public class Result {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private FootballTeam a;
    private FootballTeam b;
    private String outcome = null;

    public Result(FootballTeam a, FootballTeam b, String outcome) {
        this.a = a;
        this.b = b;
        this.outcome = outcome;
    }

    public void display() {
        logger.info(outcome);
    }

}
