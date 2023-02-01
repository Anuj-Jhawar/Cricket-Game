import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CricketGameTest {

    static void initializeVenueForTheGame(CricketGame Game, Scanner scn) {
        /*
            Initialize the venue for the game.
        */
        System.out.println("Please Enter the Venue of the Game");
        Game.setVenueForTheGame(scn.nextLine());
    }

    static void takeTeamNameInput(CricketGame Game, Scanner scn) {
        /*
            Take name of teams as input and assign them respectively.
        */
        System.out.println("Please Enter Team1 name.");
        Game.getTeam1().setTeamName(scn.nextLine());
        System.out.println("Please Enter Team2 name");
        Game.getTeam2().setTeamName(scn.nextLine());
    }

    static String completeToss(CricketGame Game) {
        /*
            Function to complete the toss for the game.
        */
        System.out.println("Time for toss");
        String TeamWhoWonTheToss = Game.initiateToss();
        String Team1 = "Team1";
        if(TeamWhoWonTheToss.equals(Team1)){
            return Game.getTeam1().getTeamName();
        }
        else
            return Game.getTeam2().getTeamName();
    }

    static void takeFormatInput(CricketGame Game, Scanner scn) {
        /*
            Function to take format of the game as an input.
        */
        System.out.println("Please Enter the format of the Game from the following:");
        System.out.println("T10, T20, ODI");
        Game.setFormatForTheGame(scn.nextLine());
    }

    static void initializeBothTheTeams(CricketGame Game, Scanner scn) {
        /*
            Initialize both the teams with player inputs.
        */
        System.out.println("Initializing Team1");
        Game.initializeTeamWithPlayersInputs(1);
        System.out.println("Initializing Team2");
        Game.initializeTeamWithPlayersInputs(2);
    }

    static int initializeNumberOfOvers(CricketGame Game) {
        /*
            Decide number of overs based on the format of the game.
        */
        String T20Format = "T20";
        String T10Format = "T10";
        int NumberofOversInGame = 0;
        String Format = Game.getFormat();
        if (Format.equals(T10Format)) {
            NumberofOversInGame = 10;
        } else if (Format.equals(T20Format)) {
            NumberofOversInGame = 20;
        } else {
            NumberofOversInGame = 50;
        }
        return NumberofOversInGame;
    }

    static Team[] assignBattingTeam(CricketGame Game, int i) {
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

    static int assignNewBatsmanIfWicketFallen(int Batsman1, int Batsman2) {
        /*
            Returns The new Batsman
        */
        return Math.max(Batsman1 + 1, Batsman2 + 1);
    }

    static ArrayList<Integer> assignBatsmanIfOverDone(int Batsman1, int Batsman2, String OutcomeOfTheBall) {
        /*
            Assigning the Batsman depending on the OutcomeOfTheBall and If Over done.
        */
        ArrayList<Integer> BatsmanOrder = new ArrayList<Integer>();
        String Wicket = "W";
        if (OutcomeOfTheBall.equals(Wicket)) {
            int NewBatsman = assignNewBatsmanIfWicketFallen(Batsman1, Batsman2);
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

    static ArrayList<Integer> assignBatsmanIfOverNotDone(int Batsman1, int Batsman2, String OutcomeOfTheBall) {
        /*
            Assigning the Batsman depending on the OutcomeOfTheBall and If Over not done.
        */
        ArrayList<Integer> BatsmanOrder = assignBatsmanIfOverDone(Batsman1, Batsman2, OutcomeOfTheBall);
        Collections.reverse(BatsmanOrder);
        return BatsmanOrder;
    }

    static ArrayList<Integer> assignBatsman(int Batsman1, int Batsman2, String OutcomeOfTheBall, int OverDone) {
        /*
            Assigning the Batsman for the upcoming ball depending on the Outcome of last ball.
        */
        if (OverDone == 1) {
            return assignBatsmanIfOverDone(Batsman1, Batsman2, OutcomeOfTheBall);
        } else {
            return assignBatsmanIfOverNotDone(Batsman1, Batsman2, OutcomeOfTheBall);
        }
    }

    static int assignBowler(CricketGame Game, int BowlingTeamIndex, int LastBowler) {
        /*
            Assigning Bowler for the next over and making sure that bowler does not repeat.
        */
        Team CurrentTeam;
        if (BowlingTeamIndex == 0)
            CurrentTeam = Game.getTeam2();
        else
            CurrentTeam = Game.getTeam1();

        int NumberOfAvailableBowlingOption = CurrentTeam.getNumberOfAllRounder() + CurrentTeam.getNumberOfBowler();
        int IndexOfChosenBowler = 10 - (int) (Math.random() * (NumberOfAvailableBowlingOption));
        return IndexOfChosenBowler;
    }

    static void assignWinnerOfTheGame(CricketGame Game) {
        /*
            Function to assign Winner of the Game
        */
        if (Game.getTeam1().getRunsScored() > Game.getTeam2().getRunsScored())
            Game.setWinner(Game.getTeam1().getTeamName());
        else
            Game.setWinner(Game.getTeam2().getTeamName());
    }

    static boolean checkIfInningIsOver(CricketGame Game, int Wickets, int target) {
        return (target != -1 && Game.getScoreOfTeam(1) > target) || Wickets == 10;
    }

    static void updateBattingAndBowlingStatsAfterEachBall(CricketGame Game, int TeamIndex, int BatsmanOnStrikeIndex, int CurrentBowler, Ball NewBall) {
        /*
            Updating the stats of batsman and bowler after every ball.
        */
        String OutcomeOfTheBallInString = Character.toString(NewBall.getOutcomeOfTheBall());
        if (NewBall.getOutcomeOfTheBall() == 'W') {
            Game.updateBattingStatsOfBatsman(TeamIndex, BatsmanOnStrikeIndex, -1);
        } else {
            Game.updateTeamBattingStats(TeamIndex, NewBall.getOutcomeOfTheBall() - '0');
            Game.updateBattingStatsOfBatsman(TeamIndex, BatsmanOnStrikeIndex, Integer.parseInt(OutcomeOfTheBallInString));
        }
        Game.updateBowlingStatsOfBowler(TeamIndex, CurrentBowler, OutcomeOfTheBallInString);
    }

    static Ball playTheBall(CricketGame Game, int TeamIndex, int BatsmanOnStrikeIndex, int CurrentBowler) {
        /*
            Playing and assigning every outcome of the ball.
        */
        Ball NewBall = new Ball();
        NewBall.assignBallOutcome();
        Game.signalOutcomeOfTheBall(NewBall.getOutcomeOfTheBall());
        updateBattingAndBowlingStatsAfterEachBall(Game, TeamIndex, BatsmanOnStrikeIndex, CurrentBowler, NewBall);
        return NewBall;
    }

    static void playAInning(CricketGame Game, int target, int NumberOfOversInGame, int i) {
        /*
            Function to play a Inning.
        */
        int BatsmanOnStrikeIndex = 0,Batsman2=0,Wickets=0;
        Ball LastBall = new Ball();
        LastBall.setOutcomeOfTheBall('0');
        int LastBowler = -1;
        for (int j = 0; j < NumberOfOversInGame; j++) {
            int CurrentBowler = assignBowler(Game, i, LastBowler);
            for (int k = 0; k < 6; k++) {
                String LastBallOutcome = Character.toString(LastBall.getOutcomeOfTheBall());
                ArrayList<Integer> BatsmanOrder = assignBatsman(BatsmanOnStrikeIndex, Batsman2, LastBallOutcome, k == 0 ? 1 : 0);
                BatsmanOnStrikeIndex = BatsmanOrder.get(0);
                Batsman2 = BatsmanOrder.get(0);
                Ball NewBall = playTheBall(Game, i, BatsmanOnStrikeIndex, CurrentBowler);
                if (NewBall.getOutcomeOfTheBall() == 'W')
                    Wickets++;
                LastBall = NewBall;
                boolean CheckIfInningsOver = checkIfInningIsOver(Game, Wickets, target);
                if (CheckIfInningsOver)
                    break;
            }
            if (Wickets == 10)
                break;
        }
    }

    static void letsPlayTheGame(CricketGame Game) {
        /*
            Function in which all the game happens.
        */
        int NumberOfOversInGame = initializeNumberOfOvers(Game);
        int target = -1;
        for (int i = 0; i < 2; i++) {
            playAInning(Game, target, NumberOfOversInGame, i);
            target = Game.getScoreOfTeam(0);
        }
        assignWinnerOfTheGame(Game);
    }

    static void printScoreCard(CricketGame Game) {
        /*
            Function to print ScoreCard
        */
        ScoreCard NewScoreCard = new ScoreCard(Game);
        NewScoreCard.printScoreCard();
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        CricketGame Game = new CricketGame();
        initializeVenueForTheGame(Game, scn);
        takeFormatInput(Game, scn);
        System.out.println("Game Start");
        takeTeamNameInput(Game, scn);
        initializeBothTheTeams(Game, scn);
        String TeamWhoWonTheToss = completeToss(Game);
        System.out.println("Toss won by" + TeamWhoWonTheToss);
        System.out.println(TeamWhoWonTheToss + " decided to bat first");
        letsPlayTheGame(Game);
        System.out.println("Team Who won the match is " + Game.getWinner());
        printScoreCard(Game);
    }
}