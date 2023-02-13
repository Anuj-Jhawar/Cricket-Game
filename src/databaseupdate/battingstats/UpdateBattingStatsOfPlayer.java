package databaseupdate.battingstats;

import cricketgame.CricketGame;
import cricketgame.Team;
import jdbc.JdbcConnection;
import player.Player;

import java.sql.Connection;

public class UpdateBattingStatsOfPlayer {
    CricketGame game;
    Player player;
    Team team;
    int outcomeOfTheBall;
    public UpdateBattingStatsOfPlayer(CricketGame game,Team team,Player player,int outcomeOfTheBall){
        this.game = game;
        this.team = team;
        this.player = player;
        this.outcomeOfTheBall = outcomeOfTheBall;
    }
    public void updateBattingStatsOfPlayer(){
        JdbcConnection jdbcConnection = new JdbcConnection();
        Connection connection = jdbcConnection.getConnection();
        UpdateBallsPlayed updateBallsPlayed = new UpdateBallsPlayed(game, player.getName(), team.getTeamName());
        updateBallsPlayed.update(outcomeOfTheBall,connection );
        if(outcomeOfTheBall==7){
            UpdateNotOutColumnInTable updateNotOutColumnInTable = new UpdateNotOutColumnInTable(game, player.getName(), team.getTeamName());
            updateNotOutColumnInTable.update(outcomeOfTheBall, connection);
        }
        else{
            UpdateRunsScored updateRunsScored = new UpdateRunsScored(game, player.getName(), team.getTeamName());
            updateRunsScored.update(outcomeOfTheBall, connection);
            if(outcomeOfTheBall==4){
                UpdateFoursScoredByPlayer updateFoursScoredByPlayer = new UpdateFoursScoredByPlayer(game, player.getName(),team.getTeamName());
                updateFoursScoredByPlayer.update(outcomeOfTheBall, connection);
            }
            else if(outcomeOfTheBall==6){
                UpdateSixesScoredByPlayer updateSixesScoredByPlayer = new UpdateSixesScoredByPlayer(game, player.getName(),team.getTeamName());
                updateSixesScoredByPlayer.update(outcomeOfTheBall,connection);
            }
        }
        UpdateStrikeRateOfPlayer updateStrikeRateOfPlayer = new UpdateStrikeRateOfPlayer(game, player.getName(), team.getTeamName(),player.getBattingStats().getScore(),player.getBattingStats().getBallsPlayed());
        updateStrikeRateOfPlayer.update(outcomeOfTheBall, connection);
        try{
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Connection not closed.");
        }
    }
}
