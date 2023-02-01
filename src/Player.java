public class Player {
    protected String Name;
    protected int Score;
    protected int BallsPlayed;
    protected double BattingStrikeRate;
    protected double BattingAverage;
    protected int NumberOfSixes;
    protected int NumberOfFours;

    void SetName(String name) {
        Name = name;
    }

    void setBallsPlayed() {
        BallsPlayed++;
    }

    void setStrikeRate() {
        BattingStrikeRate = (Score * 100.0) / BallsPlayed;
    }

    void setAverage() {
        BattingAverage = Score;
    }

    void setBoundaries(int runs) {
        if (runs == 4)
            NumberOfFours++;
        else if (runs == 6)
            NumberOfSixes++;
    }

    int getScore() {
        return Score;
    }

    void setScore(int runs) {
        Score += runs;
    }

    int getBallsPlayed() {
        return BallsPlayed;
    }

    int getNumberOfSixes() {
        return NumberOfSixes;
    }

    int getNumberOfFours() {
        return NumberOfFours;
    }

    double getBattingStrikeRate() {
        return BattingStrikeRate;
    }

    String getName() {
        return Name;
    }

    void updateBattingStats(int runs) {
        if (runs == -1) {
            updateWicket();
            return;
        }
        setScore(runs);
        setBallsPlayed();
        setAverage();
        setStrikeRate();
        setBoundaries(runs);
    }

    public void updateBowlingStats(String OutcomeOfTheBall) {

    }

    void updateWicket() {
        setBallsPlayed();
    }
}
