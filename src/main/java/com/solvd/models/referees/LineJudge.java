package com.solvd.models.referees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.models.enums.RefereePosition;
import com.solvd.models.game.Game;

public class LineJudge extends Referee {
    private static final Logger logger = LoggerFactory.getLogger(LineJudge.class);

    public LineJudge(int experience, String name) {
        super(name, experience);
        setPosition(RefereePosition.LINE_JUDGE);
    }

    @Override
    public void officiateGame(Game game) {
        train();
        logger.info(
                getPosition() + getName() + " is overseeing the sidelines for the game " + game.gameBetween());
    }

    @Override
    public String toString() {
        return getPosition() + "{name='" + name + "', experience=" + experience + "}";
    }
}
