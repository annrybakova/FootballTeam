package com.solvd.models;

import com.solvd.exceptions.IncompleteTeamException;
import com.solvd.exceptions.InvalidGameException;
import com.solvd.exceptions.SameReferees;

public class Game extends AbstractGame {
    private FieldJudge fieldJudge;
    private LineJudge lineJudge;

    public Game(FootballTeam a, FootballTeam b) {
        super(a, b);
    }

    public void setReferees(FieldJudge fieldJudge, LineJudge lineJudge) throws SameReferees {
        if (fieldJudge.equals(lineJudge)) {
            throw new SameReferees("FieldJudge and LineJudge must be different individuals!");
        }
        this.fieldJudge = fieldJudge;
        this.lineJudge = lineJudge;
    }

    @Override
    public Result play() throws InvalidGameException {
        try {
            getTeamA().validateTeam();
            getTeamB().validateTeam();
        } catch (IncompleteTeamException e) {
            throw new InvalidGameException("Game can't be pkayed: " + e.getMessage());
        }
        int result = Random.randomInt(0, 2);
        System.out.println("Result of the game: ");
        switch (result) {
            case 1:
                getTeamA().train(1);
                getTeamA().updatePlayersSkill(1);
                getTeamA().updateManagerMoney(1);
                return new Result(getTeamA(), getTeamB(), getTeamA().getFootballTeamName() + " wins");
            case 2:
                getTeamB().train(1);
                getTeamB().updatePlayersSkill(1);
                getTeamB().updateManagerMoney(1);
                return new Result(getTeamA(), getTeamB(), getTeamB().getFootballTeamName() + " wins");
            default:
                return new Result(getTeamA(), getTeamB(), " Draw");
        }
    }
}