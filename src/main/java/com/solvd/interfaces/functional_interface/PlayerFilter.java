package com.solvd.interfaces.functional_interface;

import com.solvd.models.team.FootballPlayer;

@FunctionalInterface
public interface PlayerFilter {
    boolean test(FootballPlayer player);
}