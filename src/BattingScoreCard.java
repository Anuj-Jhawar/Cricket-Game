public class BattingScoreCard implements InningScoreCard {
    Player[] BattingStats = new Player[11];

    BattingScoreCard(Team BattingTeam) {
        BattingStats = BattingTeam.Players;
    }

    void PrintHeadings() {
        /*
            Printing the headers for the Batting part of the scorecard.
        */
        String BatsmanNames = "BatsmanName";
        String Runs = "Runs";
        String Balls = "Balls";
        String Fours = "4s";
        String Sixes = "6s";
        String StrikeRate = "S.R.";
        System.out.printf("%-20s %10s %10s %10s %10s %10.6s %n", BatsmanNames, Runs, Balls, Fours, Sixes, StrikeRate);
    }

    public void ShowStats() {
        /*
            Printing the batting part of the scorecard.
        */
        PrintHeadings();
        for (Player Batsman : BattingStats) {
            if (Batsman.BallsPlayed > 0) {
                System.out.printf("%-20s %10s %10s %10s %10s %10.2f %n", Batsman.getName(), Batsman.getScore(), Batsman.getBallsPlayed(),
                        Batsman.getNumberOfFours(), Batsman.getNumberOfSixes(), Batsman.getBattingStrikeRate());
            }
        }

    }
}
