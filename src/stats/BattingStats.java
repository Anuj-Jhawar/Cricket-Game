package stats;

public class BattingStats implements Stats{

    public BattingStats(){
        score = 0;
        ballsPlayed = 0;
        battingStrikeRate = 0;
        battingAverage = 0;
        numberOfFours = 0;
        numberOfSixes = 0;
    }
    private int score;
    private int ballsPlayed;
    private double battingStrikeRate;
    private double battingAverage;
    private int numberOfSixes;
    private int numberOfFours;

    public void setBallsPlayed() {
        ballsPlayed++;
    }

    public void setStrikeRate() {
        battingStrikeRate = (score * 100.0) / ballsPlayed;
    }

    public void setAverage() {
        battingAverage = score;
    }

    public void setBoundaries(int runs) {
        if (runs == 4)
            numberOfFours++;
        else if (runs == 6)
            numberOfSixes++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int runs) {
        score += runs;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public int getNumberOfSixes() {
        return numberOfSixes;
    }

    public int getNumberOfFours() {
        return numberOfFours;
    }

    public double getBattingStrikeRate() {
        return battingStrikeRate;
    }
    public void updateBattingStats(int runs) {
        if (runs == -1) {
            setBallsPlayed();
            return;
        }
        setScore(runs);
        setBallsPlayed();
        setAverage();
        setStrikeRate();
        setBoundaries(runs);
    }

    public void updateStats(int runs){
        updateBattingStats(runs);
    }
}
