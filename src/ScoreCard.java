public class ScoreCard {
    InningScoreCard[] Innings = new InningScoreCard[4];

    public ScoreCard(CricketGame Game) {
        Innings[0] = new BattingScoreCard(Game.Team1);
        Innings[1] = new BowlingScoreCard(Game.Team2);
        Innings[2] = new BattingScoreCard(Game.Team2);
        Innings[3] = new BowlingScoreCard(Game.Team1);
    }

    void PrintScoreCard() {
        for (InningScoreCard Stats : Innings) {
            Stats.ShowStats();
        }
    }
}
