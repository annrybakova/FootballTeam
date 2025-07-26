package com.solvd.models.game;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.exceptions.InvalidGameException;

public class Competititon <T extends Game> {
    private static final Logger logger = LoggerFactory.getLogger(Competititon.class);
    private final Queue<T> matches = new LinkedList<T>();

    public void addMatch(T match) {
        matches.offer(match);
    }

    public void playAllMatches() {
        while(!matches.isEmpty()) {
            try {
                Result result = matches.poll().play();
                result.display();
            } catch (InvalidGameException e) {
                logger.error(
                        "This competition can't happen since not all the teams have enough players " + e.getMessage());
            }

        }
    }
}
