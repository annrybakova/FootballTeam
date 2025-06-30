package com.solvd.models;

public class Statistics {
    public static void displayTeamStats(FootballTeam team) {
        System.out.println("Team: " + team.getFootballTeamName());
        System.out.println("Skill Level: " + team.getTeamSkill());
        System.out.println("Players: ");
        for (FootballPlayer player : team.getTeamMembers()) {
            System.out.println("  " + player.getPlayerName() + " (Skill: " + player.getPlayerSkill() + ")");
        }
    }
}