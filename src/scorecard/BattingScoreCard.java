package scorecard;

import cricketgame.Team;
import player.Player;
import scorecard.InningScoreCard;
import stats.BattingStats;

public class BattingScoreCard implements InningScoreCard {
    Player[] battingStats = new Player[11];

    public BattingScoreCard(Team BattingTeam) {
        battingStats = BattingTeam.getPlayers();
    }

    void printHeadings() {
        /*
            Printing the headers for the Batting part of the scorecard.
        */
        String batsmanName = "BatsmanName";
        String runs = "Runs";
        String balls = "Balls";
        String fours = "4s";
        String sixes = "6s";
        String strikeRate = "S.R.";
        System.out.printf("%-20s %10s %10s %10s %10s %10.6s %n", batsmanName, runs, balls, fours, sixes, strikeRate);
    }

    public void showStats() {
        /*
            Printing the batting part of the scorecard.
        */
        printHeadings();
        for (Player batsman : battingStats) {
            BattingStats battingStats = batsman.getBattingStats();
            if (battingStats.getBallsPlayed() > 0) {
                System.out.printf("%-20s %10s %10s %10s %10s %10.2f %n", batsman.getName(), battingStats.getScore(), battingStats.getBallsPlayed(),
                        battingStats.getNumberOfFours(), battingStats.getNumberOfSixes(), battingStats.getBattingStrikeRate());
            }
        }

    }
}
