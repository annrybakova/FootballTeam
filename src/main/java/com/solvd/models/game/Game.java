package com.solvd.models.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.exceptions.IncompleteTeamException;
import com.solvd.exceptions.InvalidGameException;
import com.solvd.exceptions.SameReferees;
import com.solvd.interfaces.functional_interface.Randomise;
import com.solvd.models.enums.MatchResultType;
import com.solvd.models.referees.FieldJudge;
import com.solvd.models.referees.LineJudge;
import com.solvd.models.team.FootballTeam;
import com.solvd.models.utils.Random;

public class Game extends AbstractGame {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);
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
            throw new InvalidGameException("Game can't be played: " + e.getMessage());
        }
        Randomise MathRandom = (min, max) -> (int)(Math.random() * (max - min + 1)) + min;
        int result = Random.randomInt(0, 2, MathRandom);
        logger.info("Result of the game: ");
        switch (result) {
            case 1:
                getTeamA().train(1);
                getTeamA().updatePlayersSkill(1);
                getTeamA().updateManagerMoney(1);
                setResult(MatchResultType.WIN_TEAM_A);
                return new Result(getTeamA(), getTeamB(), getTeamA().getFootballTeamName() + " " + getResult());
            case 2:
                getTeamB().train(1);
                getTeamB().updatePlayersSkill(1);
                getTeamB().updateManagerMoney(1);
                setResult(MatchResultType.WIN_TEAM_B);
                return new Result(getTeamA(), getTeamB(), getTeamB().getFootballTeamName() + " " + getResult());
            default:
                setResult(MatchResultType.DRAW);
                return new Result(getTeamA(), getTeamB(), " " + getResult());
        }
    }
}