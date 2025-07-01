package com.solvd.models.game;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.exceptions.InvalidGameException;

public class Competititon {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private final ArrayList<Game> matches = new ArrayList<Game>();

    public void addMatch(Game match) {
        matches.add(match);
    }

    public void playAllMatches() {
        for (Game match : matches) {
            try {
                Result result = match.play();
                result.display();
            } catch (InvalidGameException e) {
                logger.error(
                        "This competition can't happen since not all the teams have enough players " + e.getMessage());
            }

        }
    }
}
