public class CricketGame {
    Team Team1 = new Team();
    Team Team2 = new Team();
    Toss TossForGame = new Toss();
    Umpire Umpire = new Umpire();
    private String Venue;
    private String Winner;
    private String Format;

    void SetVenueForTheGame(String VenueName) {
        Venue = VenueName;
    }

    void SetFormatForTheGame(String FormatType) {
        Format = FormatType;
    }

    String GetFormat() {
        return Format;
    }

    void SetWinner(String WinningTeam) {
        Winner = WinningTeam;
    }

    String GetWinner() {
        return Winner;
    }
}
