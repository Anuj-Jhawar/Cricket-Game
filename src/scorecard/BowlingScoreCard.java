package scorecard;

import cricketgame.Team;
import player.Bowler;
import player.Player;
import scorecard.InningScoreCard;
import stats.BowlingStats;

public class BowlingScoreCard implements InningScoreCard {
    Player[] players = new Player[11];

    public BowlingScoreCard(Team BowlingTeam) {
        players = BowlingTeam.getPlayers();
    }

    void printHeadings() {
        /*
            Printing the headers for the Bowling part of the scorecard.
        */
        String bowlerNames = "BowlerName";
        String runs = "Runs";
        String balls = "Balls";
        String wickets = "W";
        System.out.printf("%-20s %10s %10s %5s %n", bowlerNames, runs, balls, wickets);
    }

    public void showStats() {
        /*
            Printing the bowling part of the scorecard.
        */
        printHeadings();
        for (Player currentBowler : players) {
            BowlingStats bowlingStats = currentBowler.getBowlingStats();
            if (currentBowler instanceof Bowler && bowlingStats.getBallsBowled() > 0) {
                System.out.printf("%-20s %10s %10s %5s %n", currentBowler.getName(), bowlingStats.getRunConceded(), bowlingStats.getBallsBowled(), bowlingStats.getWickets());
            }
        }
    }
}
