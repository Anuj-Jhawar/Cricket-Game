public class Team {
    private String Name;
    Player[] Players = new Player[11];

    int RunsScored;
    int RunsConceded;
    int WicketsFallen;
    int WicketsTaken;
    Team(){
        RunsScored = 0;
        RunsConceded = 0;
        WicketsFallen = 0;
        WicketsTaken = 0;
    }

    String GetTeamName() {
        return Name;
    }

    void SetTeamName(String NameForTeam) {
        Name = NameForTeam;
    }


}
