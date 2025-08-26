package com.solvd.models.utils;

import com.solvd.interfaces.functional_interface.Randomise;

public class Random {
    public static int randomInt(int min, int max, Randomise strategy) {
        return strategy.generate(min, max);
        // return (int) (Math.random() * (max - min + 1)) + min;
    }
}