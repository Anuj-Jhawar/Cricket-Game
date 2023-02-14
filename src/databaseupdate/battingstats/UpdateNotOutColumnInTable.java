package databaseupdate.battingstats;

import cricketgame.CricketGame;
import databasequery.FindBattingStatsId;
import jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateNotOutColumnInTable implements UpdatePlayerBattingStats{
    private CricketGame game;
    private String teamName;
    private String playerName;
    public UpdateNotOutColumnInTable(CricketGame game,String playerName,String teamName){
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
            String SqlQueryToUpdateNotOut = "UPDATE BattingStats SET NotOut = 0 Where id = ?";
            try{
                statement = connection.prepareStatement(SqlQueryToUpdateNotOut);
                statement.setInt(1,battingStatsId);
                statement.executeUpdate();

            }
            catch (Exception e){
                System.out.println("Statement not prepared in databaseupdate.battingstats.UpdateNotOutColumnInTable.");
                System.out.println(e);
            }
        }
        else{
            System.out.println("Connection not established in databaseupdate.battingstats.UpdateNotOutColumnInTable.");
        }
    }
}
