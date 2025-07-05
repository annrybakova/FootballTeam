package com.solvd;

import java.util.ArrayList;

import com.solvd.exceptions.DuplicatePlayerRoleException;
import com.solvd.exceptions.IncompleteTeamException;
import com.solvd.exceptions.InsufficientFundsException;
import com.solvd.exceptions.InvalidGameException;
import com.solvd.exceptions.SameReferees;

import com.solvd.interfaces.Trackable;
import com.solvd.models.game.Competititon;
import com.solvd.models.game.Game;
import com.solvd.models.game.Result;
import com.solvd.models.market.PlayersMarket;
import com.solvd.models.referees.FieldJudge;
import com.solvd.models.referees.LineJudge;
import com.solvd.models.team.Defender;
import com.solvd.models.team.FootballPlayer;
import com.solvd.models.team.FootballTeam;
import com.solvd.models.team.Forward;
import com.solvd.models.team.Goalkeeper;
import com.solvd.models.team.Manager;
import com.solvd.models.team.RewardTracker;
import com.solvd.models.team.Trainer;
import com.solvd.models.utils.SkillsRating;
import com.solvd.models.utils.Statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        Manager manager1 = new Manager("Sr. Tomato", 10000);
        Manager manager2 = new Manager("Sr. Pumpkin", 10000);

        Trainer trainer = new Trainer("Coach Potato", 15);

        FootballTeam<FootballPlayer> team1 = new FootballTeam("Vegetables", manager1);
        FootballTeam<FootballPlayer> team2 = new FootballTeam("Fruit", manager2);

        PlayersMarket market = new PlayersMarket();
        try {
            market.addItem(new Goalkeeper("Eggplant", 80, 500));
            market.addItem(new Defender("Cucumber", 70, 400));
            market.addItem(new Forward("Apple", 60, 300));

            market.addItem(new Goalkeeper("Carrot", 80, 500));
            market.addItem(new Defender("Peach", 70, 400));
            market.addItem(new Forward("Strawberry", 60, 300));

            market.addItem(new Defender("Eggplant", 90, 600)); // Triggers DuplicationException
        } catch (DuplicatePlayerRoleException e) {
            logger.error(e.getMessage());
        }

        try {
            manager1.buyFootballPlayer(market.getPlayer("Cucumber"), team1);
            manager1.buyFootballPlayer(market.getPlayer("Eggplant"), team1);
            manager1.buyFootballPlayer(market.getPlayer("Apple"), team1);

            manager2.buyFootballPlayer(market.getPlayer("Carrot"), team2);
            manager2.buyFootballPlayer(market.getPlayer("Peach"), team2);
            manager2.buyFootballPlayer(market.getPlayer("Strawberry"), team2);
        } catch (InsufficientFundsException e) {
            logger.error(e.getMessage());
        }

        try {
            team1.validateTeam();
            team2.validateTeam();
        } catch (IncompleteTeamException e) {
            logger.error(e.getMessage());
        }

        trainer.coachTeam(team1);
        trainer.trainPlayer(team1.getTeamMembers().get(0));

        Statistics.displayTeamStats(team1);
        Statistics.displayTeamStats(team2);

        Game game = new Game(team1, team2);
        FieldJudge fieldJudge = new FieldJudge(5, "Earl Cherry");
        LineJudge lineJudge = new LineJudge(5, "Sr. Lemon");
        try {
            game.setReferees(fieldJudge, lineJudge);
            logger.info("Referees assigned successfully!");
        } catch (SameReferees e) {
            logger.error(e.getMessage());
        }

        try {
            Result result = game.play();
            result.display();
        } catch (InvalidGameException e) {
            logger.error(e.getMessage());
        }

        Competititon<Game> competition = new Competititon();
        competition.addMatch(game);

        competition.playAllMatches();
        RewardTracker rewardTracker = new RewardTracker();
        for (FootballPlayer player : team1.getTeamMembers()) {
            if (player instanceof Goalkeeper) {
                rewardTracker.giveReward(player, "Best Vegetable of the Match");
            }
        }
        rewardTracker.displayRewards();

        logger.info("------------Statistics------------");

        ArrayList<Trackable> trackableEntities = new ArrayList<>();
        trackableEntities.add(team1);
        trackableEntities.add(team2);
        for (FootballPlayer player : team1.getTeamMembers()) {
            trackableEntities.add(player);
        }
        for (FootballPlayer player : team2.getTeamMembers()) {
            trackableEntities.add(player);
        }
        trackableEntities.add(manager1);
        trackableEntities.add(manager2);

        for (Trackable entity : trackableEntities) {
            entity.displayStats();
            logger.info("------------");
        }

        logger.info("------------Rating------------");
        SkillsRating rating = new SkillsRating();
        rating.addTeamToRating(team1);
        rating.addTeamToRating(team2);
        rating.showRating();
    }

}
