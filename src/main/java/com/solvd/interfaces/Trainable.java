package com.solvd.interfaces;

public interface Trainable {
    void train(double skillIncrease);

    default void train() {
        train(1.0);
    }
}
