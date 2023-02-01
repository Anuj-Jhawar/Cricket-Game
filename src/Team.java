import java.util.Scanner;

public class Team {
    private Player[] Players = new Player[11];
    private int NumberOfBatsman;
    private int NumberOfBowler;
    private int NumberOfAllRounder;
    private int RunsScored;
    private int WicketsFallen;
    private String Name;

    Team() {
        RunsScored = 0;
        WicketsFallen = 0;
        NumberOfAllRounder = 0;
        NumberOfBatsman = 0;
        NumberOfBowler = 0;
    }

    String getTeamName() {
        return Name;
    }
    Player[] getPlayers(){
        return Players;
    }

    int GetNumberOfBatsman() {
        return NumberOfBatsman;
    }

    int getNumberOfBowler() {
        return NumberOfBowler;
    }

    int getNumberOfAllRounder() {
        return NumberOfAllRounder;
    }

    int getRunsScored() {
        return RunsScored;
    }

    int getWicketsFallen() {
        return WicketsFallen;
    }

    Player getPlayer(int index){
        return Players[index];
    }

    void setTeamName(String NameForTeam) {
        Name = NameForTeam;
    }
    void setRunsScored(int RunsScoredTillNow) {
        RunsScored += RunsScoredTillNow;
    }

    void setWicketsFallen() {
        WicketsFallen += 1;
    }

    void updateNumberOfEachPlayers(String type) {
        switch (type) {
            case "Batsman":
                NumberOfBatsman++;
                break;
            case "Bowler":
                NumberOfBowler++;
                break;
            default:
                NumberOfAllRounder++;
        }
    }

    void updateBattingStatsOfPlayer(int PlayerIndex, int runs){
        Players[PlayerIndex].updateBattingStats(runs);
    }
    void updateBowlingStatsOfPlayer(int PlayerIndex, String OutComeOfTheBall){
        Players[PlayerIndex].updateBowlingStats(OutComeOfTheBall);
    }
    void initializePlayers(){
        Scanner scn = new Scanner(System.in);
        PlayerFactory playerFactory = new PlayerFactory();
        for (int i = 0; i < 11; i++) {
            System.out.println("Please Enter Player " + (i + 1) + " Name");
            String name = scn.nextLine();
            System.out.println("Please Enter Player " + (i + 1) + " type");
            String type = scn.nextLine();
            updateNumberOfEachPlayers(type);
            Players[i] = playerFactory.getPlayer(type);
            Players[i].SetName(name);
        }
    }
}
