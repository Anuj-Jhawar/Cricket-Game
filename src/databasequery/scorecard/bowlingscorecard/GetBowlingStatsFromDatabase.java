package databasequery.scorecard.bowlingscorecard;

import cricketgame.CricketGame;
import databasequery.FindMatchId;
import databasequery.FindPlayerId;
import databasequery.FindTeamId;
import databasequery.scorecard.GetScoreCardFromDatabase;
import jdbc.JdbcConnection;
import stats.BattingStats;
import stats.BowlingStats;
import stats.Stats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetBowlingStatsFromDatabase implements GetScoreCardFromDatabase {
    Stats createBowlingStats(ResultSet resultSet){
        /*
            Create and return BowlingStats object from the resultSet.
        */
        BowlingStats bowlingStats = new BowlingStats();
        try{
            bowlingStats.setBallsBowled(resultSet.getInt("BallsBalled"));
            bowlingStats.setWickets(resultSet.getInt("Wickets"));
            bowlingStats.setRunsConceded(resultSet.getInt("RunsConceded"));
            return bowlingStats;
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Not able to create Batting stats in databasequery.scorecard.battingscorecard");
        }
        return null;
    }
    @Override
    public Stats getStats(CricketGame game, String teamName, String playerName) {
        /*
            Create and return BowlingStats object from the BowlingStats table.
        */
        JdbcConnection jdbcConnection = new JdbcConnection();
        Connection connection = jdbcConnection.getConnection();
        FindMatchId findMatchId = new FindMatchId(game);
        FindTeamId findTeamId = new FindTeamId();
        FindPlayerId findPlayerId = new FindPlayerId();
        int match_id = findMatchId.find("",connection);
        int team_id = findTeamId.find(teamName,connection);
        int player_id = findPlayerId.find(playerName,connection);
        if(connection!=null){
            PreparedStatement statement;
            try{
                String sqlQueryToFetchBattingStatsOfAPlayer = "SELECT * FROM BowlingStats WHERE player_id = ? AND team_id = ? AND match_id = ?";
                statement = connection.prepareStatement(sqlQueryToFetchBattingStatsOfAPlayer);
                statement.setInt(1,player_id);
                statement.setInt(2,team_id);
                statement.setInt(3,match_id);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return this.createBowlingStats(resultSet);
                }
                else{
                    return null;
                }
            }
            catch (Exception e){
                System.out.println(e);
                System.out.println("Not able to fetch the batting stats from database");
            }
            try{
                connection.close();
            }
            catch (Exception e){
                System.out.println(e);
                System.out.println("Connection not closed in databasequery.scorecard.battingscorecard");
            }
        }
        else{
            System.out.println("Connection not established in databasequery.scorecard.battingscorecard");
        }
        return null;
    }
}
