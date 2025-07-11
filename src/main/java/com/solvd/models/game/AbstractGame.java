package com.solvd.models.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.exceptions.InvalidGameException;
import com.solvd.models.enums.MatchResultType;
import com.solvd.models.team.FootballTeam;

public abstract class AbstractGame {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private FootballTeam teamA;
    private FootballTeam teamB;
    private MatchResultType result;

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

    public MatchResultType getResult() {
        return result;
    }

    public void setResult(MatchResultType result) {
        this. result = result;
    }

    public void setTeamA(FootballTeam teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(FootballTeam teamB) {
        this.teamB = teamB;
    }
}
