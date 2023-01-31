public class Team {
    Player[] Players = new Player[11];
    int NumberOfBatsman;
    int NumberOfBowler;
    int NumberOfAllRounder;
    int RunsScored;
    int WicketsFallen;
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

    int GetNumberOfBatsman() {
        return NumberOfBatsman;
    }

    int GetNumberOfBowler() {
        return NumberOfBowler;
    }

    int GetNumberOfAllRounder() {
        return NumberOfAllRounder;
    }

    int GetRunsScore() {
        return RunsScored;
    }

    int GetWicketsFallen() {
        return WicketsFallen;
    }

    void SetTeamName(String NameForTeam) {
        Name = NameForTeam;
    }

    //    void SetNumberOfBatsman(int CurrentNumberOfBatsman){
//        NumberOfBatsman = CurrentNumberOfBatsman;
//    }
//    void SetNumberOfBowler(int CurrentNumberOfBowler){
//        NumberOfBowler = CurrentNumberOfBowler;
//    }
//    void SetNumberOfAllRounder(int CurrentNumberOfAllRounder){
//        NumberOfAllRounder = CurrentNumberOfAllRounder;
//    }
    void SetRunsScore(int RunsScoredTillNow) {
        RunsScored = RunsScoredTillNow;
    }

    void SetWicketsFallen(int WicketsFallenTillNow) {
        WicketsFallen = WicketsFallenTillNow;
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
}
