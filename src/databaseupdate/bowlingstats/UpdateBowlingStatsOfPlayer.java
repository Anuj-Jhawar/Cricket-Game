package databaseupdate.bowlingstats;

import cricketgame.CricketGame;
import cricketgame.Team;
import jdbc.JdbcConnection;
import player.Player;

import java.sql.Connection;

public class UpdateBowlingStatsOfPlayer {
    CricketGame game;
    Player player;
    Team team;
    int outcomeOfTheBall;
    public UpdateBowlingStatsOfPlayer(CricketGame game,Player player,Team team,int outcomeOfTheBall){
        this.game = game;
        this.team = team;
        this.player = player;
        this.outcomeOfTheBall = outcomeOfTheBall;
    }
    public void updateBowlingStatsOfPlayer(){
        JdbcConnection jdbcConnection = new JdbcConnection();
        Connection connection = jdbcConnection.getConnection();
        UpdateBallsBalled updateBallsBalled = new UpdateBallsBalled(game, player.getName(), team.getTeamName());
        updateBallsBalled.update(outcomeOfTheBall, connection);
        if(outcomeOfTheBall==7){
            UpdateWicketsTakenByPlayer updateWicketsTakenByPlayer = new UpdateWicketsTakenByPlayer(game, player.getName(), team.getTeamName());
            updateWicketsTakenByPlayer.update(outcomeOfTheBall, connection);
        }
        else{
            UpdateRunsConceded updateRunsConceded = new UpdateRunsConceded(game,player.getName(), team.getTeamName());
            updateRunsConceded.update(outcomeOfTheBall,connection);
        }
        UpdateBowlingAverageOfPlayer updateBowlingAverageOfPlayer = new UpdateBowlingAverageOfPlayer(game, player.getName(), team.getTeamName(),player.getBowlingStats().getRunConceded(),player.getBowlingStats().getWickets());
        updateBowlingAverageOfPlayer.update(outcomeOfTheBall,connection);
        try{
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Connection not closed.");
        }
    }
}
