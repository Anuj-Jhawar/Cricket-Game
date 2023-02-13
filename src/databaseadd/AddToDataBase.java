package databaseadd;

import cricketgame.CricketGame;
import cricketgame.Team;
import player.Player;

public class AddToDataBase {
    CricketGame game;
    public AddToDataBase(CricketGame game){
        this.game = game;
    }
    public void addTeamToTeamTable(Team team){
        AddTeamToTeamTable addTeamToTeamTable = new AddTeamToTeamTable(team);
        addTeamToTeamTable.add();
    }
    public void addMatchToMatchTable(){
        AddMatchToMatchTable addMatchToMatchTable = new AddMatchToMatchTable(game);
        addMatchToMatchTable.add();
    }
    public void addBattingStatsToBattingStatsTable(Team team){
        String teamName = team.getTeamName();
        Player[] players = team.getPlayers();
        for(int i = 0;i<11;i++){
            AddBattingStatsToBattingStatsTable addBattingStatsToBattingStatsTable = new AddBattingStatsToBattingStatsTable(players[i].getBattingStats(),players[i].getName(),teamName,game);
            addBattingStatsToBattingStatsTable.add();
        }
    }
    public void addBowlingStatsToBowlingStatsTable(Team team){
        String teamName = team.getTeamName();
        Player[] players = team.getPlayers();
        for(int i = 0;i<11;i++){
            AddBowlingStatsToBowlingStatsTable addBowlingStatsToBowlingStatsTable = new AddBowlingStatsToBowlingStatsTable(players[i].getBowlingStats(),players[i].getName(),teamName,game);
            addBowlingStatsToBowlingStatsTable.add();
        }
    }
    public void addPlayerToPlayerTable(){
        for(int i = 0;i<2;i++){
            Team team = i==0? game.getTeam1():game.getTeam2();
            Player[] players = team.getPlayers();
            for(int j = 0;j<11;j++){
                AddPlayerToPlayerTable addPlayerToPlayerTable = new AddPlayerToPlayerTable(players[j]);
                addPlayerToPlayerTable.add();
            }
        }
    }
    public void addToDataBase(){
        this.addTeamToTeamTable(game.getTeam1());
        this.addTeamToTeamTable(game.getTeam2());
        this.addMatchToMatchTable();
        this.addPlayerToPlayerTable();
        this.addBowlingStatsToBowlingStatsTable(game.getTeam1());
        this.addBowlingStatsToBowlingStatsTable(game.getTeam2());
        this.addBattingStatsToBattingStatsTable(game.getTeam1());
        this.addBattingStatsToBattingStatsTable(game.getTeam2());

    }
}
