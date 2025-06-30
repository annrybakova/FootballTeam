package com.solvd.exceptions;

public class IncompleteTeamException extends RuntimeException {
    public IncompleteTeamException(String message) {
        super(message);
    }
}