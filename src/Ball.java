public class Ball {
    private char OutcomeOfTheBall;
    private String BatsmanName;
    private String BowlerName;
    private String FielderName;
    private boolean IsANoBall;

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

    char GetOutcomeOfTheBall(){
        return OutcomeOfTheBall;
    }

}
