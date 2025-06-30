package com.solvd.models;

import com.solvd.exceptions.InvalidGameException;

public abstract class AbstractGame {
    private FootballTeam teamA;
    private FootballTeam teamB;

    public AbstractGame(FootballTeam teamA, FootballTeam teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public abstract Result play() throws InvalidGameException;

    public String gameBetween() {
        return teamA.getFootballTeamName() + " vs " + teamB.getFootballTeamName();
    }

    public FootballTeam getTeamA() {
        return teamA;
    }

    public FootballTeam getTeamB() {
        return teamB;
    }

    public void setTeamA(FootballTeam teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(FootballTeam teamB) {
        this.teamB = teamB;
    }
}
