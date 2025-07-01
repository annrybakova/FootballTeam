package com.solvd.models.referees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.models.game.Game;

public class FieldJudge extends Referee {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public FieldJudge(int experience, String name) {
        super(name, experience);
    }

    @Override
    public void officiateGame(Game game) {
        train();
        logger.info("Field Referee " + getName() + " is officiating the game " + game.gameBetween());
    }

    @Override
    public String toString() {
        return "Field Referee{name='" + name + "', experience=" + experience + "}";
    }
}
