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

    String GetTeamName() {
        return Name;
    }
    Player[] getPlayers(){
        return Players;
    }

    int GetNumberOfBatsman() {
        return NumberOfBatsman;
    }

    int GetNumberOfBowler() {
        return NumberOfBowler;
    }

    int GetNumberOfAllRounder() {
        return NumberOfAllRounder;
    }

    int GetRunsScored() {
        return RunsScored;
    }

    int GetWicketsFallen() {
        return WicketsFallen;
    }

    Player getPlayer(int index){
        return Players[index];
    }

    void SetTeamName(String NameForTeam) {
        Name = NameForTeam;
    }
    void SetRunsScored(int RunsScoredTillNow) {
        RunsScored += RunsScoredTillNow;
    }

    void SetWicketsFallen() {
        WicketsFallen += 1;
    }

    void UpdateNumberOfEachPlayers(String type) {
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

    void UpdateBattingStatsOfPlayer(int PlayerIndex,int runs){
        Players[PlayerIndex].UpdateBattingStats(runs);
    }
    void UpdateBowlingStatsOfPlayer(int PlayerIndex,String OutComeOfTheBall){
        Players[PlayerIndex].UpdateBowlingStats(OutComeOfTheBall);
    }
    void InitializePlayers(){
        Scanner scn = new Scanner(System.in);
        PlayerFactory playerFactory = new PlayerFactory();
        for (int i = 0; i < 11; i++) {
            System.out.println("Please Enter Player " + (i + 1) + " Name");
            String name = scn.nextLine();
            System.out.println("Please Enter Player " + (i + 1) + " type");
            String type = scn.nextLine();
            UpdateNumberOfEachPlayers(type);
            Players[i] = playerFactory.getPlayer(type);
            Players[i].SetName(name);
        }
    }
}
