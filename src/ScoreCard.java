public class ScoreCard {
    InningScoreCard[] Innings = new InningScoreCard[4];

    public ScoreCard(CricketGame Game) {
        Innings[0] = new BattingScoreCard(Game.getTeam1());
        Innings[1] = new BowlingScoreCard(Game.getTeam2());
        Innings[2] = new BattingScoreCard(Game.getTeam2());
        Innings[3] = new BowlingScoreCard(Game.getTeam1());
    }

    void printScoreCard() {
        for (InningScoreCard Stats : Innings) {
            Stats.showStats();
        }
    }
}
