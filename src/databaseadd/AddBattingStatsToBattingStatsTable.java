package databaseadd;

import cricketgame.CricketGame;
import databasequery.FindMatchId;
import databasequery.FindPlayerId;
import databasequery.FindTeamId;
import jdbc.JdbcConnection;
import stats.BattingStats;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBattingStatsToBattingStatsTable implements AddToTable {
    private BattingStats battingStats;
    private String playerName;
    private String teamName;
    private CricketGame game;
    public AddBattingStatsToBattingStatsTable(BattingStats battingStats,String playerName,String teamName,CricketGame game){
        this.battingStats = battingStats;
        this.playerName = playerName;
        this.teamName = teamName;
        this.game = game;
    }
    @Override
    public void add() {
        /*
            First check if the stats are already present of not.
        */
        JdbcConnection jdbcConnection = new JdbcConnection();
        Connection connection = jdbcConnection.getConnection();
        FindMatchId findMatchId = new FindMatchId(game);
        int matchId = findMatchId.find("",connection);
        FindTeamId findTeamId = new FindTeamId();
        int teamId = findTeamId.find(teamName,connection);
        FindPlayerId findPlayerId = new FindPlayerId();
        int playerId = findPlayerId.find(playerName,connection);
        if(connection!=null){
            PreparedStatement statement;
            try{
                String sqlCommandToInsertBattingStatsInBattingStatsTable = "INSERT INTO BattingStats (player_id, team_id, match_id, RunsScored, BallsPlayed,Fours,Sixes,NotOut,StrikeRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sqlCommandToInsertBattingStatsInBattingStatsTable);
                statement.setInt(1, playerId);
                statement.setInt(2, teamId);
                statement.setInt(3, matchId);
                statement.setInt(4,battingStats.getScore());
                statement.setInt(5,battingStats.getBallsPlayed());
                statement.setInt(6,battingStats.getNumberOfFours());
                statement.setInt(7,battingStats.getNumberOfSixes());
                statement.setInt(8,1);
                statement.setDouble(9,0);
                try {
                    statement.executeUpdate();
                }
                catch (Exception e){
                    System.out.println("Query not completed.");
                }
            }
            catch (Exception e){
                System.out.println("Statement not created.");
            }
            finally {
                try{
                    //connection.close();
                }
                catch (Exception e){
                    System.out.println("Connection not closed.");
                }
            }
        }
        else{
            System.out.println("Connection not established");
        }
    }
}
