import java.util.ArrayList;

public class BowlingScoreCard implements InningScoreCard{
    Player[] BowlingStats = new Player[11];

    BowlingScoreCard(Team BowlingTeam){
        BowlingStats = BowlingTeam.Players;
    }
    void PrintHeadings(){
        /*
            Printing the headers for the Bowling part of the scorecard.
        */
        String BowlerNames = "BowlerName";
        String Runs = "Runs";
        String Balls = "Balls";
        String Wickets = "W";
        System.out.printf("%-20s %10s %10s %5s %n",BowlerNames,Runs,Balls,Wickets);
    }
    public void ShowStats(){
        /*
            Printing the bowling part of the scorecard.
        */
        PrintHeadings();
        for(Player CurrentBowler : BowlingStats){
            if(CurrentBowler instanceof Bowler &&  ((Bowler) CurrentBowler).BallsBowled>0){
                System.out.printf("%-20s %10s %10s %5s %n",CurrentBowler.getName(),((Bowler) CurrentBowler).getRunConceded(),((Bowler) CurrentBowler).getBallsBowled(),((Bowler) CurrentBowler).getWickets());
            }
        }
    }
}
