package com.solvd.interfaces.functional_interface;

import com.solvd.models.team.Manager;

@FunctionalInterface
public interface EarningsCalculator {
    double calculate(double baseScore, Manager manager);
}
