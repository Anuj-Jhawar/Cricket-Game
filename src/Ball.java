public class Ball {
    char OutcomeOfTheBall;
    String BatsmanName;
    String BowlerName;
    String FielderName;
    boolean IsANoBall;

    Ball(){
        OutcomeOfTheBall = 'N';
        BatsmanName = "Not defined";
        BowlerName = "Not defined";
        FielderName = "Not defined";
        IsANoBall = false;
    }

    void AssignBallOutcome(){
        /*
            Assigning the outcome of the ball.
        */
        int BallOutcome = (int) (Math.random()*8);
        if(BallOutcome==7){
            OutcomeOfTheBall = 'W';
        }
        else {
            OutcomeOfTheBall =  (char) (BallOutcome+'0');
        }
    }

}
