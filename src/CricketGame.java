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

    void setVenueForTheGame(String VenueName) {
        Venue = VenueName;
    }

    void setFormatForTheGame(String FormatType) {
        Format = FormatType;
    }

    String initiateToss() {
        return TossForGame.callForToss();
    }

    String getFormat() {
        return Format;
    }

    void signalOutcomeOfTheBall(char OutcomeOfTheBall) {
        Umpire.signal(OutcomeOfTheBall);
    }

    void setWinner(String WinningTeam) {
        Winner = WinningTeam;
    }

    void updateTeamBattingStats(int index, int OutComeOfTheBall) {
        /*
            Updating the Team Batting Stats.
        */
        if (index == 0) {
            Team1.setRunsScored(OutComeOfTheBall);
        } else {
            Team2.setRunsScored(OutComeOfTheBall);
        }
    }

    void updateTeamWicketsFallen(int index) {
        /*
            Updating the Team WicketFallen Stats.
        */
        if (index == 0) {
            Team1.setWicketsFallen();
        } else {
            Team1.setWicketsFallen();
        }
    }

    void updateBattingStatsOfBatsman(int TeamIndex, int PlayerIndex, int runs) {
        /*
            Updating the batsman stats depending on the runs scored on the ball.
        */
        if (TeamIndex == 0) {
            Team1.updateBattingStatsOfPlayer(PlayerIndex, runs);
        } else {
            Team2.updateBattingStatsOfPlayer(PlayerIndex, runs);
        }
    }

    void updateBowlingStatsOfBowler(int TeamIndex, int PlayerIndex, String OutcomeOfTheBall) {
        /*
            Updating the bowler stats depending on the outcome of the ball.
        */
        if (TeamIndex == 0) {
            Team2.updateBowlingStatsOfPlayer(PlayerIndex, OutcomeOfTheBall);
        } else {
            Team1.updateBowlingStatsOfPlayer(PlayerIndex, OutcomeOfTheBall);
        }
    }

    int getScoreOfTeam(int index) {
        /*
            Returning the target set by the Batting team.
        */
        if (index == 0)
            return Team1.getRunsScored();
        else
            return Team2.getRunsScored();
    }

    void initializeTeamWithPlayersInputs(int CurrentTeam) {
        /*
            Initializing the Teams with PLayer inputs.
        */
        if (CurrentTeam == 1) {
            Team1.initializePlayers();
        } else {
            Team2.initializePlayers();
        }
    }

    String getWinner() {
        /*
            Returning the winner of the game.
        */
        return Winner;
    }
}
