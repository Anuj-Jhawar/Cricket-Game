public class CricketGame {
    private String Venue;
    private String Winner;
    Team Team1 = new Team();
    Team Team2 = new Team();
    Toss TossForGame = new Toss();
    private String Format;
    Umpire Umpire = new Umpire();
    void SetVenueForTheGame(String VenueName){
        Venue = VenueName;
    }
    void SetFormatForTheGame(String FormatType){
        Format = FormatType;
    }
    String GetFormat(){
        return Format;
    }
    void SetWinner(String WinningTeam){
        Winner = WinningTeam;
    }
    String GetWinner(){
        return Winner;
    }
}
