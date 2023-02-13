package databasequery;

import cricketgame.CricketGame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindBowlingStatsId implements QueryDatabase{
    private CricketGame game;
    private String teamName;
    private String playerName;

    public FindBowlingStatsId(CricketGame game,String playerName,String teamName){
        this.game = game;
        this.playerName = playerName;
        this.teamName = teamName;
    }
    @Override
    public int find(String queryTypeName, Connection connection) {
        FindMatchId findMatchId = new FindMatchId(game);
        FindTeamId findTeamId = new FindTeamId();
        FindPlayerId findPlayerId = new FindPlayerId();
        int matchId = findMatchId.find("",connection);
        int teamId = findTeamId.find(teamName,connection);
        int playerId = findPlayerId.find(playerName,connection);
        if(connection!=null){
            PreparedStatement statement;
            try{
                String sqlCommandToGetBowlingStatsId = "SELECT * FROM BowlingStats WHERE player_id = ? AND team_id = ?  AND match_id = ?";
                try {
                    statement = connection.prepareStatement(sqlCommandToGetBowlingStatsId);
                    statement.setInt(1,playerId);
                    statement.setInt(2,teamId);
                    statement.setInt(3,matchId);
                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next())
                    return resultSet.getInt("id");
                    else return 0;
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
        return 1;
    }
}