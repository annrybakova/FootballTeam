package com.solvd.models;

import java.util.ArrayList;

import com.solvd.exceptions.IncompleteTeamException;
import com.solvd.interfaces.Trackable;
import com.solvd.interfaces.Trainable;

public class FootballTeam implements Trainable, Trackable {
    private String name;
    private Manager manager;
    private ArrayList<FootballPlayer> team = new ArrayList<FootballPlayer>();
    private int teamSkillBonus = 0;

    public FootballTeam(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
    }

    public void addFootballPlayer(FootballPlayer player) {
        team.add(player);
    }

    public int getTeamSkill() {
        int teamSkill = 0;
        for (FootballPlayer player : team) {
            teamSkill += player.getPlayerSkill();
        }
        return teamSkill + teamSkillBonus;
    }

    @Override
    public void train(double score) {
        this.teamSkillBonus += score;
        System.out.println(name + " won and increased team skill by " + score);
    }

    public void updatePlayersSkill(int score) {
        System.out.println("Skills of all members of team " + name + " are increased by " + team.size() / score);
        for (FootballPlayer member : team) {
            member.updatePlayerSkill(team.size() / score);
        }
    }

    public void updateManagerMoney(int score) {
        System.out.println("Manager of team " + name + " earned $" + team.size() / score);
        manager.earnMoney(team.size() / score);
    }

    public String getFootballTeamName() {
        return name;
    }

    public ArrayList<FootballPlayer> getTeamMembers() {
        return team;
    }

    public void setFootballTeamName(String name) {
        this.name = name;
    }

    public void setTeamMembers(ArrayList<FootballPlayer> team) {
        this.team = team;
    }

    public void validateTeam() {
        boolean hasGoalkeeper = false;
        boolean hasDefender = false;
        boolean hasForward = false;
        for (FootballPlayer player : team) {
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
        System.out.println("Team: " + name);
        System.out.println("Manager: " + manager.getName());
        System.out.println("Total Skill Level: " + getTeamSkill());
        System.out.println("Players:");
        for (FootballPlayer player : team) {
            System.out.println("  " + player.getPlayerName() + " (Skill: " + player.getPlayerSkill() + ")");
        }
    }
}
