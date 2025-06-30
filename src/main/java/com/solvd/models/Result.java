package com.solvd.models;

public class Result {
    private FootballTeam a;
    private FootballTeam b;
    private String outcome = null;

    public Result(FootballTeam a, FootballTeam b, String outcome) {
        this.a = a;
        this.b = b;
        this.outcome = outcome;
    }

    public void display() {
        System.out.println(outcome);
    }

}
