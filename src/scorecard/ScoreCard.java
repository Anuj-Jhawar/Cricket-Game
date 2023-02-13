package scorecard;

import cricketgame.CricketGame;
import scorecard.BattingScoreCard;
import scorecard.BowlingScoreCard;
import scorecard.InningScoreCard;

public class ScoreCard {
    InningScoreCard[] innings = new InningScoreCard[4];

    public ScoreCard(CricketGame Game) {
        if(Game.getBattingTeamIndex()==1){
            innings[0] = new BattingScoreCard(Game.getTeam1());
            innings[1] = new BowlingScoreCard(Game.getTeam2());
            innings[2] = new BattingScoreCard(Game.getTeam2());
            innings[3] = new BowlingScoreCard(Game.getTeam1());
        }
        else{
            innings[0] = new BattingScoreCard(Game.getTeam2());
            innings[1] = new BowlingScoreCard(Game.getTeam1());
            innings[2] = new BattingScoreCard(Game.getTeam1());
            innings[3] = new BowlingScoreCard(Game.getTeam2());
        }
    }

    public void printScoreCard() {
        for (InningScoreCard stats : innings) {
            stats.showStats();
        }
    }
}
