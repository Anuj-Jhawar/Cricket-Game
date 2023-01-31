public class AllRounder extends Player implements UpdatingBowlingStats {
    int RunConceded;

    int Wickets;

    double BallsBowled;

    double BowlingStrikeRate;

    double BowlingAverage;

    AllRounder() {
        Score = 0;
        BallsPlayed = 0;
        BattingStrikeRate = 0;
        BattingAverage = 0.0;
        RunConceded = 0;
        Wickets = 0;
        BallsBowled = 0;
        BowlingAverage = 0.0;
        BowlingStrikeRate = 0.0;
    }

    public void updateBowlingAverage() {
        BowlingAverage = (RunConceded * 1.0) / (Wickets);
    }

    public void updateWickets() {
        Wickets++;
    }

    public void updateBowlingStrikeRate() {
        BowlingStrikeRate = (BallsBowled) / Wickets;
    }

    public void updateRunsConceded(int run) {
        RunConceded += run;
    }

    public void updateBallsBowled() {
        BallsBowled++;
    }

    public void UpdateBowlingStats(String OutcomeOfTheBall) {
        updateBallsBowled();
        if (OutcomeOfTheBall == "W")
            updateWickets();
        else
            updateRunsConceded(Integer.getInteger(OutcomeOfTheBall));
        updateBowlingAverage();
        updateBowlingStrikeRate();
    }
}
