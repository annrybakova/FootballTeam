package com.solvd.interfaces;

import com.solvd.models.team.FootballPlayer;
import com.solvd.models.team.FootballTeam;

public interface Coachable {
    void coachTeam(FootballTeam team);

    void trainPlayer(FootballPlayer player);
}