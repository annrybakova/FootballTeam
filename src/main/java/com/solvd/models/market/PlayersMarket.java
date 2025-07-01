package com.solvd.models.market;

import com.solvd.exceptions.DuplicatePlayerRoleException;
import com.solvd.models.team.FootballPlayer;

public class PlayersMarket extends AbstractMarket<FootballPlayer> {

    @Override
    public FootballPlayer getItem(String name) {
        for (FootballPlayer player : items) {
            if (player.getPlayerName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public FootballPlayer getPlayer(String name) {
        return getItem(name);
    }

    @Override
    public void addItem(FootballPlayer player) throws DuplicatePlayerRoleException {
        for (FootballPlayer existingPlayer : items) {
            if (existingPlayer.getPlayerName().equals(player.getPlayerName())
                    && !existingPlayer.getClass().equals(player.getClass())) {
                throw new DuplicatePlayerRoleException(
                        "Player " + player.getPlayerName() + " already exists with a different role: "
                                + existingPlayer.getClass().getSimpleName() + ". New role: "
                                + player.getClass().getSimpleName());
            }
        }
        items.add(player);
    }
}