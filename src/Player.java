public class Player {
    protected String Name;
    protected int Score;
    protected int BallsPlayed;
    protected double BattingStrikeRate;
    protected double BattingAverage;

    void SetName(String name) {
        Name = name;
    }

    void setScore(int runs){
        Score += runs;
    }
    void setBallsPlayed(){
        BallsPlayed++;
    }
    void setStrikeRate(){
        BattingStrikeRate = (Score*1.0)/BallsPlayed;
    }
    void setAverage(){
        BattingAverage = (double) Score;
    }

}
