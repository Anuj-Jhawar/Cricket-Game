package cricketgame;

import input.FormatInput;
import input.InputInterface;
import input.TeamNameInput;
import input.VenueInput;
import storeteam.TeamMap;

public class CricketGame {
    private Team team1;
    private Team team2;
    private Toss tossForGame = new Toss();
    private Umpire umpire = new Umpire();
    private String venue;
    private String winner;
    private String format;

    CricketGame(){
        InputInterface TakeFormatInput = new FormatInput(this);
        InputInterface TakeVenueInput = new VenueInput(this);
        TakeVenueInput.collectInput();
        TakeFormatInput.collectInput();
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setVenueForTheGame(String venueName) {
        venue = venueName;
    }

    public void setFormatForTheGame(String formatType) {
        format = formatType;
    }

    public String initiateToss() {
        return tossForGame.callForToss();
    }
    public String getFormat() {
        return format;
    }

    public void signalOutcomeOfTheBall(int outcomeOfTheBall) {
        umpire.signal(outcomeOfTheBall);
    }

    public void setWinner(String winningTeam) {
        winner = winningTeam;
    }

    public void updateTeamBattingStats(int index, int outComeOfTheBall) {
        /*
            Updating the Others.Team Batting Stats.
        */
        if (index == 0) {
            team1.setRunsScored(outComeOfTheBall);
        } else {
            team2.setRunsScored(outComeOfTheBall);
        }
    }

    public void updateTeamWicketsFallen(int index) {
        /*
            Updating the Others.Team WicketFallen Stats.
        */
        if (index == 0) {
            team1.setWicketsFallen();
        } else {
            team1.setWicketsFallen();
        }
    }

    public void updateBattingStatsOfBatsman(int teamIndex, int playerIndex, int runs) {
        /*
            Updating the batsman stats depending on the runs scored on the ball.
        */
        if (teamIndex == 0) {
            team1.updateBattingStatsOfPlayer(playerIndex, runs);
        } else {
            team2.updateBattingStatsOfPlayer(playerIndex, runs);
        }
    }

    public void updateBowlingStatsOfBowler(int teamIndex, int playerIndex, int outcomeOfTheBall) {
        /*
            Updating the bowler stats depending on the outcome of the ball.
        */
        if (teamIndex == 0) {
            team2.updateBowlingStatsOfPlayer(playerIndex, outcomeOfTheBall);
        } else {
            team1.updateBowlingStatsOfPlayer(playerIndex, outcomeOfTheBall);
        }
    }

    public int getScoreOfTeam(int index) {
        /*
            Returning the target set by the Batting team.
        */
        if (index == 0)
            return team1.getRunsScored();
        else
            return team2.getRunsScored();
    }
    public String getWinner() {
        /*
            Returning the winner of the game.
        */
        return winner;
    }
    public Team setTeamForTheGame(){
        InputInterface TakeTeamNameInput = new TeamNameInput();
        String TeamName = TakeTeamNameInput.collectInput();
        Team team = TeamMap.getTeamMap().getTeam(TeamName);
        team.setTeamName(TeamName);
        if(TeamMap.getTeamMap().containsTeam(TeamName)==false)
            team.updateNumberOfEachPlayers();
        else
            team.addBattingAndBowlingStatsForEachPlayer();
        return team;
    }
    public void setTeamsForTheGame(){
        team1 = setTeamForTheGame();
        team2 = setTeamForTheGame();
    }
}
