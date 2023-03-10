package databaseupdate.battingstats;

import cricketgame.CricketGame;
import databasequery.FindBattingStatsId;
import jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateStrikeRateOfPlayer implements UpdatePlayerBattingStats{
    private CricketGame game;
    private String teamName;
    private String playerName;
    private int runsScored;
    private int ballsPlayed;
    public UpdateStrikeRateOfPlayer(CricketGame game,String playerName,String teamName,int runsScored,int ballsPlayed){
        this.game = game;
        this.playerName = playerName;
        this.teamName = teamName;
        this.runsScored = runsScored;
        this.ballsPlayed = ballsPlayed;
    }
    @Override
    public void update(int stats, Connection connection) {
        /*
            Update the StrikeRate of Batsman in database.
        */
        FindBattingStatsId findBattingStatsId = new FindBattingStatsId(game,playerName,teamName);
        int battingStatsId = findBattingStatsId.find("",connection);

        if(connection!=null){
            PreparedStatement statement;
            double strikeRate = (runsScored*1.0)/(ballsPlayed);
            String SqlQueryToUpdateStrikeRateOfPlayer = "UPDATE BattingStats SET StrikeRate = ? Where id = ?";
            try{
                statement = connection.prepareStatement(SqlQueryToUpdateStrikeRateOfPlayer);
                statement.setDouble(1,strikeRate);
                statement.setInt(2,battingStatsId);
                statement.executeUpdate();

            }
            catch (Exception e){
                System.out.println("Statement not prepared in databaseupdate.battingstats.UpdateStrikeRateOfPlayer.");
                System.out.println(e);
            }
        }
        else{
            System.out.println("Connection not established in databaseupdate.battingstats.UpdateStrikeRateOfPlayer.");
        }
    }
}
