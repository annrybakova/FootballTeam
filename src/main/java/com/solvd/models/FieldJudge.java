package com.solvd.models;

public class FieldJudge extends Referee {

    public FieldJudge(int experience, String name) {
        super(name, experience);
    }

    @Override
    public void officiateGame(Game game) {
        train();
        System.out.println("Field Referee " + getName() + " is officiating the game " + game.gameBetween());
    }

    @Override
    public String toString() {
        return "Field Referee{name='" + name + "', experience=" + experience + "}";
    }
}
