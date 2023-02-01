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

    void UpdateTeamBattingStats(int index, int OutComeOfTheBall){
        if(index==0){
            Team1.SetRunsScored(OutComeOfTheBall);
        }
        else{
            Team2.SetRunsScored(OutComeOfTheBall);
        }
    }

    void UpdateTeamWicketsFallen(int index){
        if(index==0){
            Team1.SetWicketsFallen();
        }
        else{
            Team1.SetWicketsFallen();;
        }
    }

    void UpdateBattingStatsOfBatsman(int TeamIndex, int PlayerIndex, int runs){
        if(TeamIndex==0){
            Team1.UpdateBattingStatsOfPlayer(PlayerIndex,runs);
        }
        else{
            Team2.UpdateBattingStatsOfPlayer(PlayerIndex,runs);
        }
    }

    void UpdateBowlingStatsOfBowler(int TeamIndex, int PlayerIndex,String OutcomeOfTheBall){
        if(TeamIndex==0){
            Team1.UpdateBowlingStatsOfPlayer(PlayerIndex,OutcomeOfTheBall);
        }
        else{
            Team2.UpdateBowlingStatsOfPlayer(PlayerIndex,OutcomeOfTheBall);
        }
    }

    String GetWinner() {
        return Winner;
    }
}
