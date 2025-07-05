package com.solvd.models.team;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.exceptions.IncompleteTeamException;
import com.solvd.interfaces.Trackable;
import com.solvd.interfaces.Trainable;

public class FootballTeam<T extends FootballPlayer> implements Trainable, Trackable, Comparable<FootballTeam> {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private String name;
    private Manager manager;
    private ArrayList<T> team = new ArrayList<T>();
    private int teamSkillBonus = 0;

    public FootballTeam(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
    }

    public void addFootballPlayer(T player) {
        team.add(player);
    }

    public int getTeamSkill() {
        int teamSkill = 0;
        for (T player : team) {
            teamSkill += player.getPlayerSkill();
        }
        return teamSkill + teamSkillBonus;
    }

    @Override
    public void train(double score) {
        this.teamSkillBonus += score;
        logger.info(name + " won and increased team skill by " + score);
    }

    public void updatePlayersSkill(int score) {
        logger.info("Skills of all members of team " + name + " are increased by " + team.size() / score);
        for (T member : team) {
            member.updatePlayerSkill(team.size() / score);
        }
    }

    public void updateManagerMoney(int score) {
        logger.info("Manager of team " + name + " earned $" + team.size() / score);
        manager.earnMoney(team.size() / score);
    }

    public String getFootballTeamName() {
        return name;
    }

    public ArrayList<T> getTeamMembers() {
        return team;
    }

    public void setFootballTeamName(String name) {
        this.name = name;
    }

    public void setTeamMembers(ArrayList<T> team) {
        this.team = team;
    }

    public void validateTeam() {
        boolean hasGoalkeeper = false;
        boolean hasDefender = false;
        boolean hasForward = false;
        for (T player : team) {
            if (player instanceof Goalkeeper) {
                hasGoalkeeper = true;
            } else if (player instanceof Defender) {
                hasDefender = true;
            } else if (player instanceof Forward) {
                hasForward = true;
            }
        }
        if (!hasGoalkeeper || !hasDefender || !hasForward) {
            throw new IncompleteTeamException(
                    "Team " + name + " must include at least 1 Goalkeeper, 1 Defender, and 1 Forward.");
        }
    }

    @Override
    public void displayStats() {
        logger.info("Team: " + name);
        logger.info("Manager: " + manager.getName());
        logger.info("Total Skill Level: " + getTeamSkill());
        logger.info("Players:");
        for (T player : team) {
            logger.info("  " + player.getPlayerName() + " (Skill: " + player.getPlayerSkill() + ")");
        }
    }

    @Override
    public int compareTo(FootballTeam other) {
        return this.name.compareTo(other.name);
    }
}
