public class Team {
    String Name;
    Player[] Players = new Player[11];

    Team(){
        for(int i = 0;i<11;i++){
            Players[i] = new Player();
        }
    }
    int RunScored;

}
