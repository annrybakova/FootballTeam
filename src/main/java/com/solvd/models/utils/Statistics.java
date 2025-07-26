package com.solvd.models.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.models.team.FootballPlayer;
import com.solvd.models.team.FootballTeam;

public class Statistics {
    private static final Logger logger = LoggerFactory.getLogger(Statistics.class);

    public static <T extends FootballPlayer> void displayTeamStats(FootballTeam <T> team) {
        logger.info("Team: " + team.getFootballTeamName());
        logger.info("Skill Level: " + team.getTeamSkill());
        logger.info("Players: ");
        for (FootballPlayer player : team.getTeamMembers()) {
            logger.info("  " + player.getPlayerName() + " (Skill: " + player.getPlayerSkill() + ")");
        }
    }
}