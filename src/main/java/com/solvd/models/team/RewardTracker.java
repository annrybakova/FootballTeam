package com.solvd.models.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;
import com.solvd.interfaces.Rewardable;

public class RewardTracker {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private Map<FootballPlayer, List<String>> rewardsTracker = new HashMap<>();

    public void giveReward(FootballPlayer player, String reward) {
        if (!rewardsTracker.containsKey(player)) {
            List<String> newRewards = new ArrayList<>();
            newRewards.add(reward);
            rewardsTracker.put(player, newRewards);
        } else {
            rewardsTracker.get(player).add(reward);
        }
        if (player instanceof Rewardable) {
            ((Rewardable) player).giveReward(reward);
        }
        logger.info(player.getPlayerName() + " received reward: " + reward);
    }

    public void displayRewards() {
        if (rewardsTracker.isEmpty()) {
            logger.info("  No rewards yet.");
        } else {
            // for (Map.Entry<FootballPlayer, List<String>> entry :
            // rewardsTracker.entrySet()) {
            // logger.info(" Given to: " + entry.getKey().getPlayerName());
            // for (String reward : entry.getValue()) {
            // logger.info(" - " + reward);
            // }
            // }
            rewardsTracker.entrySet().stream()
                    .forEach(entry -> {
                        logger.info("  Given to: " + entry.getKey().getPlayerName());
                        entry.getValue().stream()
                                .forEach(reward -> logger.info("    - " + reward));
                    });
        }
    }

    public Map<FootballPlayer, List<String>> getAllRewards() {
        return rewardsTracker;
    }
}
