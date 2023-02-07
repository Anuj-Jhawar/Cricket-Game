package stats;

import input.InputInterface;
import input.PlayerNameInput;

public class BowlingStats implements UpdateStats{
    private int runConceded;

    private int wickets;

    private int ballsBowled;

    private double bowlingStrikeRate;

    private double bowlingAverage;
    public BowlingStats() {
        runConceded = 0;
        wickets = 0;
        ballsBowled = 0;
        bowlingAverage = 0.0;
        bowlingStrikeRate = 0.0;
    }

    public void updateBowlingAverage() {
        bowlingAverage = (runConceded * 1.0) / (wickets);
    }

    public void updateWickets() {
        wickets++;
    }

    public void updateBowlingStrikeRate() {
        bowlingStrikeRate = (ballsBowled * 1.0) / wickets;
    }

    public void updateRunsConceded(int runs) {
        runConceded += runs;
    }

    public void updateBallsBowled() {
        ballsBowled++;
    }

    public int getRunConceded() {
        return runConceded;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public int getWickets() {
        return wickets;
    }

    public void updateBowlingStats(int outcomeOfTheBall) {
        /*
            Function to invoke all the batting stats of the bowler.
        */
        updateBallsBowled();
        if (outcomeOfTheBall==7)
            updateWickets();
        else
            updateRunsConceded(outcomeOfTheBall);
        updateBowlingAverage();
        updateBowlingStrikeRate();
    }
    public void updateStats(int runs){

    }
}
