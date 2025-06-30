package com.solvd.models;

public class Defender extends FootballPlayer {
    private static final String type = "Defender";

    public Defender(String name, int skill, int price) {
        super(name, skill, price);
    }

    @Override
    public void beEnrolled(FootballTeam team) {
        System.out.println(getPlayerName() + " joined team " + team.getFootballTeamName() + " as a " + type);
    }

}
