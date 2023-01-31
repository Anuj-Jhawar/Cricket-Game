import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class CricketGameTest {
    static PlayerFactory playerFactory = new PlayerFactory();

    static void InitializeVenueForTheGame(CricketGame Game, Scanner scn) {
        /*
            Initialize the venue for the game.
        */
        System.out.println("Please Enter the Venue of the Game");
        Game.SetVenueForTheGame(scn.nextLine());
    }

    static void TakeTeamNameInput(CricketGame Game, Scanner scn) {
        /*
            Take name of teams as input and assign them respectively.
        */
        System.out.println("Please Enter Team1 name.");
        Game.Team1.SetTeamName(scn.nextLine());
        System.out.println("Please Enter Team2 name");
        Game.Team2.SetTeamName(scn.nextLine());
    }

    static String CompleteToss(CricketGame Game) {
        /*
            Function to complete the toss for the game.
        */
        System.out.println("Time for toss");
        String TeamWhoWonTheToss = Game.TossForGame.CallForToss();
        return TeamWhoWonTheToss;
    }

    static void TakeFormatInput(CricketGame Game, Scanner scn) {
        /*
            Function to take format of the game as an input.
        */
        System.out.println("Please Enter the format of the Game from the following:");
        System.out.println("T10, T20, ODI");
        Game.SetFormatForTheGame(scn.nextLine());
    }

    static void InitializeTeamWithPlayersInputs(Team CurrentTeam, Scanner scn) {
        /*
            Initialize the teams with player details as per input.
        */
        for (int i = 0; i < 11; i++) {
            System.out.println("Please Enter Player " + (i + 1) + " Name");
            String name = scn.nextLine();
            System.out.println("Please Enter Player " + (i + 1) + " type");
            String type = scn.nextLine();
            CurrentTeam.UpdateNumberOfEachPlayers(type);
            CurrentTeam.Players[i] = playerFactory.getPlayer(type);
            CurrentTeam.Players[i].SetName(name);
        }
    }

    static void InitializeBothTheTeams(CricketGame Game, Scanner scn) {
        /*
            Initialize both the teams with player inputs.
        */
        System.out.println("Initializing Team1");
        InitializeTeamWithPlayersInputs(Game.Team1, scn);
        System.out.println("Initializing Team2");
        InitializeTeamWithPlayersInputs(Game.Team2, scn);
    }

    static int InitializeNumberOfOvers(CricketGame Game) {
        /*
            Decide number of overs based on the format of the game.
        */
        String T20Format = "T20";
        String T10Format = "T10";
        int NumberofOversInGame = 0;
        String Format = Game.GetFormat();
        if (Format.equals(T10Format)) {
            NumberofOversInGame = 10;
        } else if (Format.equals(T20Format)) {
            NumberofOversInGame = 20;
        } else {
            NumberofOversInGame = 50;
        }
        return NumberofOversInGame;
    }

    static Team[] AssignBattingTeam(CricketGame Game, int i) {
        /*
            Assigning the batting team for the inning.
        */
        Team[] TeamOrder = new Team[2];
        if (i == 0) {
            TeamOrder[0] = Game.Team1;
            TeamOrder[1] = Game.Team2;
        } else {
            TeamOrder[0] = Game.Team2;
            TeamOrder[1] = Game.Team1;
        }
        return TeamOrder;
    }

    static int AssignNewBatsmanIfWicketFallen(int Batsman1, int Batsman2) {
        /*
            Returns The new Batsman
        */
        return Math.max(Batsman1 + 1, Batsman2 + 1);
    }

    static ArrayList<Integer> AssignBatsmanIfOverDone(int Batsman1, int Batsman2, String OutcomeOfTheBall) {
        /*
            Assigning the Batsman depending on the OutcomeOfTheBall and If Over done.
        */
        ArrayList<Integer> BatsmanOrder = new ArrayList<Integer>();
        String Wicket = "W";
        if (OutcomeOfTheBall.equals(Wicket)) {
            int NewBatsman = AssignNewBatsmanIfWicketFallen(Batsman1, Batsman2);
            BatsmanOrder.add(Batsman2);
            BatsmanOrder.add(NewBatsman);
        } else {
            int RunsScoredOnTheBall = Integer.parseInt(OutcomeOfTheBall);
            if (RunsScoredOnTheBall % 2 == 0) {
                BatsmanOrder.add(Batsman2);
                BatsmanOrder.add(Batsman1);
            } else {
                BatsmanOrder.add(Batsman1);
                BatsmanOrder.add(Batsman2);
            }
        }
        return BatsmanOrder;
    }

    static ArrayList<Integer> AssignBatsmanIfOverNotDone(int Batsman1, int Batsman2, String OutcomeOfTheBall) {
        /*
            Assigning the Batsman depending on the OutcomeOfTheBall and If Over not done.
        */
        ArrayList<Integer> BatsmanOrder = AssignBatsmanIfOverDone(Batsman1,Batsman2,OutcomeOfTheBall);
        Collections.reverse(BatsmanOrder);
        return BatsmanOrder;
    }

    static ArrayList<Integer> AssignBatsman(int Batsman1, int Batsman2, String OutcomeOfTheBall, int OverDone) {
        /*
            Assigning the Batsman for the upcoming ball depending on the Outcome of last ball.
        */
        if (OverDone == 1) {
            return AssignBatsmanIfOverDone(Batsman1, Batsman2, OutcomeOfTheBall);
        } else {
            return AssignBatsmanIfOverNotDone(Batsman1, Batsman2, OutcomeOfTheBall);
        }
    }

    static Player AssignBowler(Team CurrentTeam, Player LastBowler) {
        /*
            Assigning Bowler for the next over and making sure that bowler does not repeat.
        */
        int NumberOfAvailableBowlingOption = CurrentTeam.GetNumberOfAllRounder() + CurrentTeam.GetNumberOfBowler();
        int IndexOfChosenBowler = 10 - (int) (Math.random() * (NumberOfAvailableBowlingOption));
        return CurrentTeam.Players[IndexOfChosenBowler];
    }

    static void AssignWinnerOfTheGame(CricketGame Game) {
        /*
            Function to assign Winner of the Game
        */
        if (Game.Team1.RunsScored > Game.Team2.RunsScored)
            Game.SetWinner(Game.Team1.GetTeamName());
        else
            Game.SetWinner(Game.Team2.GetTeamName());
    }

    static void LetsPlayTheGame(CricketGame Game) {
        /*
            Function in which all the game happens.
        */
        int NumberOfOversInGame = 0;
        int target = -1;
        NumberOfOversInGame = InitializeNumberOfOvers(Game);
        for (int i = 0; i < 2; i++) {
            Team[] TeamOrder = AssignBattingTeam(Game, i);
            Team BattingTeam = TeamOrder[0];
            Team BowlingTeam = TeamOrder[1];
            int BatsmanOnStrikeIndex = 0;
            int Batsman2 = 0;
            int Wickets = 0;
            Ball LastBall = new Ball();
            LastBall.setOutcomeOfTheBall('0');
            for (int j = 0; j < NumberOfOversInGame; j++) {
                Bowler CurrentBowler = (Bowler) AssignBowler(BowlingTeam, null);
                for (int k = 0; k < 6; k++) {
                    Ball NewBall = new Ball();
                    String LastBallOutcome = Character.toString(LastBall.GetOutcomeOfTheBall());
                    ArrayList<Integer> BatsmanOrder = AssignBatsman(BatsmanOnStrikeIndex, Batsman2, LastBallOutcome, k == 0 ? 1 : 0);
                    BatsmanOnStrikeIndex = BatsmanOrder.get(0);
                    Batsman2 = BatsmanOrder.get(0);
                    Player BatsmanOnStrike = BattingTeam.Players[BatsmanOnStrikeIndex];
                    NewBall.AssignBallOutcome();
                    Game.Umpire.Signal(NewBall.GetOutcomeOfTheBall());
                    char OutcomeOfTheBall = NewBall.GetOutcomeOfTheBall();
                    String OutcomeOfTheBallInString = Character.toString(OutcomeOfTheBall);
                    if (NewBall.GetOutcomeOfTheBall() == 'W') {
                        BatsmanOnStrike.UpdateWicket();
                        Wickets += 1;
                    } else {
                        BattingTeam.RunsScored += (NewBall.GetOutcomeOfTheBall() - '0');
                        BatsmanOnStrike.UpdateBattingStats(Integer.parseInt(OutcomeOfTheBallInString));
                    }
                    LastBall = NewBall;
                    CurrentBowler.UpdateBowlingStats(OutcomeOfTheBallInString);
                    if (target != -1 && BattingTeam.RunsScored > target)
                        break;
                    if (Wickets == 10)
                        break;
                }
                if (Wickets == 10)
                    break;
            }
            target = BattingTeam.RunsScored;
        }
        AssignWinnerOfTheGame(Game);
    }

    static void PrintScoreCard(CricketGame Game) {
        /*
            Function to print ScoreCard
        */
        ScoreCard NewScoreCard = new ScoreCard(Game);
        NewScoreCard.PrintScoreCard();
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        CricketGame Game = new CricketGame();
        InitializeVenueForTheGame(Game, scn);
        TakeFormatInput(Game, scn);
        System.out.println("Game Start");
        TakeTeamNameInput(Game, scn);
        InitializeBothTheTeams(Game, scn);
        String TeamWhoWonTheToss = CompleteToss(Game);
        System.out.println("Toss won by" + TeamWhoWonTheToss);
        System.out.println(TeamWhoWonTheToss + " decided to bat first");
        LetsPlayTheGame(Game);
        System.out.println("Team Who won the match is " + Game.GetWinner());
        PrintScoreCard(Game);
    }
}