package com.solvd.interfaces;

import com.solvd.models.FootballPlayer;
import com.solvd.models.FootballTeam;

public interface Coachable {
    void coachTeam(FootballTeam team);

    void trainPlayer(FootballPlayer player);
}