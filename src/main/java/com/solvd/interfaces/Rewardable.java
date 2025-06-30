package com.solvd.interfaces;

import java.util.List;

public interface Rewardable {
    void giveReward(String reward);

    void displayRewards();

    List<String> getRewards();
}