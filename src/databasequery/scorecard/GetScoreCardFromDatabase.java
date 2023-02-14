package databasequery.scorecard;

import cricketgame.CricketGame;
import stats.Stats;

public interface GetScoreCardFromDatabase{
    Stats getStats(CricketGame game,String teamName,String playerName);
}
