package databaseupdate.battingstats;

import cricketgame.CricketGame;
import databasequery.FindBattingStatsId;
import jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateSixesScoredByPlayer implements UpdatePlayerBattingStats{
    private CricketGame game;
    private String teamName;
    private String playerName;
    public UpdateSixesScoredByPlayer(CricketGame game,String playerName,String teamName){
        this.game = game;
        this.playerName = playerName;
        this.teamName = teamName;
    }
    @Override
    public void update(int stats, Connection connection) {
        FindBattingStatsId findBattingStatsId = new FindBattingStatsId(game,playerName,teamName);
        int battingStatsId = findBattingStatsId.find("",connection);
        if(connection!=null){
            PreparedStatement statement;
            String SqlQueryToUpdateNumberOfSixesPlayed = "UPDATE BattingStats SET Sixes = Sixes+? Where id = ?";
            try{
                statement = connection.prepareStatement(SqlQueryToUpdateNumberOfSixesPlayed);
                statement.setInt(1,stats);
                statement.setInt(2,battingStatsId);
                statement.executeUpdate();

            }
            catch (Exception e){
                System.out.println("Statement not prepared.");
                System.out.println(e);
            }
        }
        else{
            System.out.println("Connection not established.");
        }
    }
}
