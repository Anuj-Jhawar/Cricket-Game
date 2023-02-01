import java.util.ArrayList;
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
        Game.getTeam1().SetTeamName(scn.nextLine());
        System.out.println("Please Enter Team2 name");
        Game.getTeam2().SetTeamName(scn.nextLine());
    }

    static String CompleteToss(CricketGame Game) {
        /*
            Function to complete the toss for the game.
        */
        System.out.println("Time for toss");
        String TeamWhoWonTheToss = Game.InitiateToss();
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

    static void InitializeBothTheTeams(CricketGame Game, Scanner scn) {
        /*
            Initialize both the teams with player inputs.
        */
        System.out.println("Initializing Team1");
        Game.InitializeTeamWithPlayersInputs(1);
        System.out.println("Initializing Team2");
        Game.InitializeTeamWithPlayersInputs(2);
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
            TeamOrder[0] = Game.getTeam1();
            TeamOrder[1] = Game.getTeam2();
        } else {
            TeamOrder[0] = Game.getTeam2();
            TeamOrder[1] = Game.getTeam1();
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
        ArrayList<Integer> BatsmanOrder = AssignBatsmanIfOverDone(Batsman1, Batsman2, OutcomeOfTheBall);
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

    static int AssignBowler(CricketGame Game, int BowlingTeamIndex, Player LastBowler) {
        /*
            Assigning Bowler for the next over and making sure that bowler does not repeat.
        */
        Team CurrentTeam;
        if (BowlingTeamIndex == 0)
            CurrentTeam = Game.getTeam2();
        else
            CurrentTeam = Game.getTeam1();

        int NumberOfAvailableBowlingOption = CurrentTeam.GetNumberOfAllRounder() + CurrentTeam.GetNumberOfBowler();
        int IndexOfChosenBowler = 10 - (int) (Math.random() * (NumberOfAvailableBowlingOption));
        return IndexOfChosenBowler;
    }

    static void AssignWinnerOfTheGame(CricketGame Game) {
        /*
            Function to assign Winner of the Game
        */
        if (Game.getTeam1().GetRunsScored() > Game.getTeam2().GetRunsScored())
            Game.SetWinner(Game.getTeam1().GetTeamName());
        else
            Game.SetWinner(Game.getTeam2().GetTeamName());
    }

    static boolean CheckIfInningsOver(CricketGame Game, int Wickets, int target) {
        return (target != -1 && Game.getScoreOfTeam(1) > target) || Wickets == 10;
    }

    static void UpdateBattingAndBowlingStatsAfterEachBall(CricketGame Game, int TeamIndex, int BatsmanOnStrikeIndex, int CurrentBowler, Ball NewBall) {
        /*
            Updating the stats of batsman and bowler after every ball.
        */
        String OutcomeOfTheBallInString = Character.toString(NewBall.GetOutcomeOfTheBall());
        if (NewBall.GetOutcomeOfTheBall() == 'W') {
            Game.UpdateBattingStatsOfBatsman(TeamIndex, BatsmanOnStrikeIndex, -1);
        } else {
            Game.UpdateTeamBattingStats(TeamIndex, NewBall.GetOutcomeOfTheBall() - '0');
            Game.UpdateBattingStatsOfBatsman(TeamIndex, BatsmanOnStrikeIndex, Integer.parseInt(OutcomeOfTheBallInString));
        }
        Game.UpdateBowlingStatsOfBowler(TeamIndex, CurrentBowler, OutcomeOfTheBallInString);
    }

    static Ball PlayTheBall(CricketGame Game, int TeamIndex, int BatsmanOnStrikeIndex, int CurrentBowler) {
        /*
            Playing and assigning every outcome of the ball.
        */
        Ball NewBall = new Ball();
        NewBall.AssignBallOutcome();
        Game.signalOutcomeOfTheBall(NewBall.GetOutcomeOfTheBall());
        UpdateBattingAndBowlingStatsAfterEachBall(Game, TeamIndex, BatsmanOnStrikeIndex, CurrentBowler, NewBall);
        return NewBall;
    }

    static void playAInning(CricketGame Game, int target, int NumberOfOversInGame, int i) {
        /*
            Function to play a Inning.
        */
        int BatsmanOnStrikeIndex = 0;
        int Batsman2 = 0;
        int Wickets = 0;
        Ball LastBall = new Ball();
        LastBall.setOutcomeOfTheBall('0');
        for (int j = 0; j < NumberOfOversInGame; j++) {
            int CurrentBowler = AssignBowler(Game, i, null);
            for (int k = 0; k < 6; k++) {

                String LastBallOutcome = Character.toString(LastBall.GetOutcomeOfTheBall());
                ArrayList<Integer> BatsmanOrder = AssignBatsman(BatsmanOnStrikeIndex, Batsman2, LastBallOutcome, k == 0 ? 1 : 0);
                BatsmanOnStrikeIndex = BatsmanOrder.get(0);
                Batsman2 = BatsmanOrder.get(0);

                Ball NewBall = PlayTheBall(Game, i, BatsmanOnStrikeIndex, CurrentBowler);
                if (NewBall.GetOutcomeOfTheBall() == 'W')
                    Wickets++;
                LastBall = NewBall;

                boolean CheckIfInningsOver = CheckIfInningsOver(Game, Wickets, target);
                if (CheckIfInningsOver)
                    break;
            }
            if (Wickets == 10)
                break;
        }
    }

    static void LetsPlayTheGame(CricketGame Game) {
        /*
            Function in which all the game happens.
        */
        int NumberOfOversInGame = InitializeNumberOfOvers(Game);
        int target = -1;
        for (int i = 0; i < 2; i++) {
            playAInning(Game, target, NumberOfOversInGame, i);
            target = Game.getScoreOfTeam(0);
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