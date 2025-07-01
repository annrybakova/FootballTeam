package com.solvd.models.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.models.team.FootballPlayer;
import com.solvd.models.team.FootballTeam;

public class Statistics {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void displayTeamStats(FootballTeam team) {
        logger.info("Team: " + team.getFootballTeamName());
        logger.info("Skill Level: " + team.getTeamSkill());
        logger.info("Players: ");
        for (FootballPlayer player : team.getTeamMembers()) {
            logger.info("  " + player.getPlayerName() + " (Skill: " + player.getPlayerSkill() + ")");
        }
    }
}