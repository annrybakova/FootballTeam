package com.solvd.models.utils;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.models.team.FootballTeam;

public class SkillsRating {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public TreeMap<FootballTeam, Integer> rating = new TreeMap<>();

    public void addTeamToRating(FootballTeam team) {
        int skill = team.getTeamSkill();
        rating.put(team, skill);
    }

    public TreeMap<FootballTeam, Integer> showRating() {
        // for (Map.Entry<FootballTeam, Integer> team : rating.entrySet()) {
        // logger.info(team.getKey().getFootballTeamName() + " - " + team.getValue());
        // }
        rating.entrySet().stream()
                .forEach(entry -> logger.info(entry.getKey().getFootballTeamName() + " - " + entry.getValue()));

        return rating;
    }
}
