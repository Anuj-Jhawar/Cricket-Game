public class Bowler extends Player implements UpdatingBowlingStats {
    private int RunConceded;

    private int Wickets;

    private int BallsBowled;

    private double BowlingStrikeRate;

    private double BowlingAverage;

    Bowler() {
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
        BowlingStrikeRate = (BallsBowled * 1.0) / Wickets;
    }

    public void updateRunsConceded(int run) {
        RunConceded += run;
    }

    public void updateBallsBowled() {
        BallsBowled++;
    }

    int getRunConceded() {
        return RunConceded;
    }

    int getBallsBowled() {
        return BallsBowled;
    }

    int getWickets() {
        return Wickets;
    }

    public void UpdateBowlingStats(String OutcomeOfTheBall) {
        /*
            Function to invoke all the batting stats of the bowler.
        */
        updateBallsBowled();
        String Wicket = "W";
        if (OutcomeOfTheBall.equals(Wicket))
            updateWickets();
        else
            updateRunsConceded(Integer.parseInt(OutcomeOfTheBall));
        updateBowlingAverage();
        updateBowlingStrikeRate();
    }
}
