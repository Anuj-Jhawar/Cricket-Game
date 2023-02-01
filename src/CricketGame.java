public class CricketGame {
    private final Team Team1 = new Team();
    private final Team Team2 = new Team();
    private final Toss TossForGame = new Toss();
    private final Umpire Umpire = new Umpire();
    private String Venue;
    private String Winner;
    private String Format;

    Team getTeam1() {
        return Team1;
    }

    Team getTeam2() {
        return Team2;
    }

    void SetVenueForTheGame(String VenueName) {
        Venue = VenueName;
    }

    void SetFormatForTheGame(String FormatType) {
        Format = FormatType;
    }

    String InitiateToss() {
        return TossForGame.CallForToss();
    }

    String GetFormat() {
        return Format;
    }

    void signalOutcomeOfTheBall(char OutcomeOfTheBall) {
        Umpire.Signal(OutcomeOfTheBall);
    }

    void SetWinner(String WinningTeam) {
        Winner = WinningTeam;
    }

    void UpdateTeamBattingStats(int index, int OutComeOfTheBall) {
        /*
            Updating the Team Batting Stats.
        */
        if (index == 0) {
            Team1.SetRunsScored(OutComeOfTheBall);
        } else {
            Team2.SetRunsScored(OutComeOfTheBall);
        }
    }

    void UpdateTeamWicketsFallen(int index) {
        /*
            Updating the Team WicketFallen Stats.
        */
        if (index == 0) {
            Team1.SetWicketsFallen();
        } else {
            Team1.SetWicketsFallen();
        }
    }

    void UpdateBattingStatsOfBatsman(int TeamIndex, int PlayerIndex, int runs) {
        /*
            Updating the batsman stats depending on the runs scored on the ball.
        */
        if (TeamIndex == 0) {
            Team1.UpdateBattingStatsOfPlayer(PlayerIndex, runs);
        } else {
            Team2.UpdateBattingStatsOfPlayer(PlayerIndex, runs);
        }
    }

    void UpdateBowlingStatsOfBowler(int TeamIndex, int PlayerIndex, String OutcomeOfTheBall) {
        /*
            Updating the bowler stats depending on the outcome of the ball.
        */
        if (TeamIndex == 0) {
            Team2.UpdateBowlingStatsOfPlayer(PlayerIndex, OutcomeOfTheBall);
        } else {
            Team1.UpdateBowlingStatsOfPlayer(PlayerIndex, OutcomeOfTheBall);
        }
    }

    int getScoreOfTeam(int index) {
        /*
            Returning the target set by the Batting team.
        */
        if (index == 0)
            return Team1.GetRunsScored();
        else
            return Team2.GetRunsScored();
    }

    void InitializeTeamWithPlayersInputs(int CurrentTeam) {
        /*
            Initializing the Teams with PLayer inputs.
        */
        if (CurrentTeam == 1) {
            Team1.InitializePlayers();
        } else {
            Team2.InitializePlayers();
        }
    }

    String GetWinner() {
        /*
            Returning the winner of the game.
        */
        return Winner;
    }
}
