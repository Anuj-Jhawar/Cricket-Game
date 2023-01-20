public class Team {
    private String Name;
    Player[] Players = new Player[11];

    Team(){
        for(int i = 0;i<11;i++){
            Players[i] = new Player();
        }
    }

    String GetTeamName(){
        return Name;
    }

    void SetTeamName(String NameForTeam){
        Name = NameForTeam;
    }

    int RunScored;

}
