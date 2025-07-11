package com.solvd.models.referees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.models.enums.RefereePosition;
import com.solvd.models.game.Game;

public class FieldJudge extends Referee {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public FieldJudge(int experience, String name) {
        super(name, experience);
        setPosition(RefereePosition.FIELD_JUDGE);
    }

    @Override
    public void officiateGame(Game game) {
        train();
        logger.info(getPosition() + " " + getName() + " is officiating the game " + game.gameBetween());
    }

    @Override
    public String toString() {
        return getPosition() + " {name='" + name + "', experience=" + experience + "}";
    }
}
