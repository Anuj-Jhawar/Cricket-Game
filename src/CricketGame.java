public class CricketGame {
    private Team Team1 = new Team();
    private Team Team2 = new Team();
    private Toss TossForGame = new Toss();
    private Umpire Umpire = new Umpire();
    private String Venue;
    private String Winner;
    private String Format;

    Team getTeam1(){
        return Team1;
    }
    Team getTeam2(){
        return Team2;
    }
    void SetVenueForTheGame(String VenueName) {
        Venue = VenueName;
    }

    void SetFormatForTheGame(String FormatType) {
        Format = FormatType;
    }

    String InitiateToss(){
        return TossForGame.CallForToss();
    }

    String GetFormat() {
        return Format;
    }

    void signalOutcomeOfTheBall(char OutcomeOfTheBall){
        Umpire.Signal(OutcomeOfTheBall);
    }

    void SetWinner(String WinningTeam) {
        Winner = WinningTeam;
    }

    String GetWinner() {
        return Winner;
    }
}
