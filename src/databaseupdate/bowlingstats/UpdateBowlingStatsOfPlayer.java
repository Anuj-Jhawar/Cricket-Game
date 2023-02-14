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
    public void updateWicketsTakenByPlayer(Connection connection){
        UpdateWicketsTakenByPlayer updateWicketsTakenByPlayer = new UpdateWicketsTakenByPlayer(game, player.getName(), team.getTeamName());
        updateWicketsTakenByPlayer.update(outcomeOfTheBall, connection);
    }
    public void updateBallsBalled(Connection connection){
        UpdateBallsBalled updateBallsBalled = new UpdateBallsBalled(game, player.getName(), team.getTeamName());
        updateBallsBalled.update(outcomeOfTheBall, connection);
    }
    public void updateRunsConceded(Connection connection){
        UpdateRunsConceded updateRunsConceded = new UpdateRunsConceded(game,player.getName(), team.getTeamName());
        updateRunsConceded.update(outcomeOfTheBall,connection);
    }
    public void updateBowlingAverageOfPlayer(Connection connection){
        UpdateBowlingAverageOfPlayer updateBowlingAverageOfPlayer = new UpdateBowlingAverageOfPlayer(game, player.getName(), team.getTeamName(),player.getBowlingStats().getRunConceded(),player.getBowlingStats().getWickets());
        updateBowlingAverageOfPlayer.update(outcomeOfTheBall,connection);
    }
    public void updateBowlingStatsOfPlayer(){
        /*
            Update all the stats of Bowler in database.
        */
        JdbcConnection jdbcConnection = new JdbcConnection();
        Connection connection = jdbcConnection.getConnection();
        this.updateBallsBalled(connection);
        if(outcomeOfTheBall==7){
         this.updateWicketsTakenByPlayer(connection);
        }
        else{
            this.updateRunsConceded(connection);
        }
        this.updateBowlingAverageOfPlayer(connection);
        try{
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Connection not closed.");
        }
    }
}
