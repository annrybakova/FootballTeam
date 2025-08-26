package com.solvd.models.market;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.exceptions.DuplicatePlayerRoleException;
import com.solvd.models.team.FootballPlayer;

public class PlayersMarket extends AbstractMarket<FootballPlayer> {
    private static final Logger logger = LoggerFactory.getLogger(PlayersMarket.class);

    @Override
    public FootballPlayer getItem(String name) {
        return items.stream().filter(player -> player.getPlayerName().equals(name)).findFirst().orElse(null);
        // if (player.getPlayerName().equals(name)) {
        // return player;
        // }
    }

    public FootballPlayer getPlayer(String name) {
        return getItem(name);
    }

    @Override
    public void addItem(FootballPlayer player) throws DuplicatePlayerRoleException {
        // for (FootballPlayer existingPlayer : items) {
        // if (existingPlayer.getPlayerName().equals(player.getPlayerName())
        // && !existingPlayer.getClass().equals(player.getClass())) {
        // throw new DuplicatePlayerRoleException(
        // "Player " + player.getPlayerName() + " already exists with a different role:
        // "
        // + existingPlayer.getClass().getSimpleName() + ". New role: "
        // + player.getClass().getSimpleName());
        // }
        // }
        FootballPlayer existingPlayer = items.stream()
                .filter(p -> p.getPlayerName().equals(player.getPlayerName())
                        && !p.getClass().equals(player.getClass()))
                .findFirst()
                .orElse(null);

        if (existingPlayer != null) {
            throw new DuplicatePlayerRoleException(
                    "Player " + player.getPlayerName() + " already exists with a different role: "
                            + existingPlayer.getClass().getSimpleName() + ". New role: "
                            + player.getClass().getSimpleName());
        }
        if (items.add(player)) {
            logger.info(player.getPlayerName() + " is added to the Market");
        } else {
            logger.info(player.getPlayerName() + " has been already added to the Market");
        }
    }
}