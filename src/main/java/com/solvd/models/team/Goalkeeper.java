package com.solvd.models.team;

import java.util.ArrayList;
import java.util.List;

import com.solvd.interfaces.Rewardable;
import com.solvd.models.enums.PlayerPosition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Goalkeeper extends FootballPlayer implements Rewardable {
    private static final Logger logger = LoggerFactory.getLogger(Goalkeeper.class);
    private List<String> rewards = new ArrayList<>();

    public Goalkeeper(String name, int skill, int price) {
        super(name, skill, price);
        setPosition(PlayerPosition.GOALKEEPER);
    }

    @Override
    public void beEnrolled(FootballTeam team) {
        logger.info(getPosition() + " " + getPlayerName() + " joined team " + team.getFootballTeamName());
    }

    @Override
    public void giveReward(String reward) {
        rewards.add(reward);
        logger.info(name + " received reward: " + reward);
    }

    @Override
    public List<String> getRewards() {
        return rewards;
    }

    @Override
    public void displayRewards() {
        logger.info(getPlayerName() + "'s Awards:");
        if (rewards.isEmpty()) {
            logger.info("  No rewards yet.");
        } else {
            for (String reward : rewards) {
                logger.info("  - " + reward);
            }
        }
    }

    @Override
    public void displayStats() {
        super.displayStats();
        displayRewards();
    }
}
