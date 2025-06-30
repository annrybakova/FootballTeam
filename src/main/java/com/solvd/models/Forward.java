package com.solvd.models;

public class Forward extends FootballPlayer {
    private static final String type = "Forward";

    public Forward(String name, int skill, int price) {
        super(name, skill, price);
    }

    @Override
    public void beEnrolled(FootballTeam team) {
        System.out.println(type + getPlayerName() + " joined team " + team.getFootballTeamName());
    }
}
