import java.util.Scanner;
public class CricketGameTest {
    static void InitializeVenueForTheGame(CricketGame Game, Scanner scn) {
        /*
            Initialize the venue for the game.
        */
        System.out.println("Please Enter the Venue of the Game");
        Game.Venue = scn.nextLine();
    }
    static void TakeTeamNameInput(CricketGame Game, Scanner scn){
        /*
            Take name of teams as input and assign them respectively.
        */
        System.out.println("Please Enter Team1 name.");
        Game.Team1.Name = scn.nextLine();
        System.out.println("Please Enter Team2 name");
        Game.Team2.Name = scn.nextLine();
    }
    static String CompleteToss(CricketGame Game){
        /*
            Function to complete the toss for the game.
        */
        System.out.println("Time for toss");
        String TeamWhoWonTheToss = Game.TossForGame.CallForToss();
        return TeamWhoWonTheToss;
    }
    static void TakeFormatInput(CricketGame Game, Scanner scn){
        /*
            Function to take format of the game as an input.
        */
        System.out.println("Please Enter the format of the Game from the following:");
        System.out.println("T10, T20, ODI");
        Game.Format = scn.nextLine();
    }
    static void InitializeTeamWithPlayersInputs(Team CurrentTeam, Scanner scn){
        /*
            Initialize the teams with player details as per input.
        */
        for(int i = 0;i<11;i++) {
            System.out.println("Please Enter Player " + (i+1) + " Name");
            CurrentTeam.Players[i].Name = scn.nextLine();
        }
    }
    static void InitializeBothTheTeams(CricketGame Game, Scanner scn){
        /*
            Initialize both the teams with player inputs.
        */
        System.out.println("Initializing Team1");
        InitializeTeamWithPlayersInputs(Game.Team1,scn);
        System.out.println("Initializing Team2");
        InitializeTeamWithPlayersInputs(Game.Team2,scn);
    }

    static int InitializeNumberOfOvers(CricketGame Game) {
        /*
            Decide number of overs based on the format of the game.
        */
        int NumberofOversInGame = 0;
        if(Game.Format == "T10"){
            NumberofOversInGame = 10;
        } else if(Game.Format == "T20") {
            NumberofOversInGame = 20;
        }else{
            NumberofOversInGame = 50;
        }
        return NumberofOversInGame;
    }

    static char AssignBallOutcome(){
        /*
            Assigning the outcome of the ball.
        */
        int BallOutcome = (int) (Math.random()*8);
        if(BallOutcome==7){
            return 'W';
        }
        else {
             return (char) (BallOutcome+'0');
        }

    }

    static Team AssignBattingTeam(CricketGame Game, int i){
        /*
            Assigning the batting team for the inning.
        */
        if(i==0)
            return Game.Team1;
        return Game.Team2;
    }

    static void LetsPlayTheGame(CricketGame Game) {
        /*
            Function in which all the game happens.
        */
        int NumberOfOversInGame = 0;
        NumberOfOversInGame = InitializeNumberOfOvers(Game);
        for(int i = 0;i<2;i++){
            //int BatsmanNumber1 = 0;
            //int BatsmanNumber2 = 1;
            //int Bowler = 10;
            Team NewTeam = AssignBattingTeam(Game,i);
            int Wickets = 0;
            for(int j = 0;j<NumberOfOversInGame;j++){
                for(int k = 0;k<6;k++){
                    Ball NewBall = new Ball();
                    NewBall.AssignBallOutcome();
                    Game.Umpire.Signal(NewBall.OutcomeOfTheBall);
                    //System.out.println("Ball outcome is " + NewBall.OutcomeOfTheBall);
                    if(NewBall.OutcomeOfTheBall=='W')
                        Wickets += 1;
                    else
                        NewTeam.RunScored += (NewBall.OutcomeOfTheBall - '0');
                    if(Wickets==10)
                        break;
                }
            }
        }
        if(Game.Team1.RunScored > Game.Team2.RunScored)
            Game.Winner = Game.Team1.Name;
        else
            Game.Winner = Game.Team2.Name;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        CricketGame Game = new CricketGame();
        InitializeVenueForTheGame(Game,scn);
        TakeFormatInput(Game , scn);
        System.out.println("Game Start");
        TakeTeamNameInput(Game,scn);
        InitializeBothTheTeams(Game,scn);
        String TeamWhoWonTheToss = CompleteToss(Game);
        System.out.println("Toss won by" + TeamWhoWonTheToss);
        System.out.println(TeamWhoWonTheToss + " decided to bat first");
        LetsPlayTheGame(Game);
        System.out.println("Team Who won the match is " + Game.Winner);
    }
}