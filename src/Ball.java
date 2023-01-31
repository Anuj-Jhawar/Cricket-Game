public class Ball {
    private char OutcomeOfTheBall;
    private String BatsmanName;
    private String BowlerName;
    private String FielderName;
    private boolean IsANoBall;

    Ball() {
        OutcomeOfTheBall = 'N';
        BatsmanName = "Not defined";
        BowlerName = "Not defined";
        FielderName = "Not defined";
        IsANoBall = false;
    }

    void AssignBallOutcome() {
        /*
            Assigning the outcome of the ball.
        */
        int BallOutcome = (int) (Math.random() * 100);
        if (BallOutcome >= 95) {
            OutcomeOfTheBall = 'W';
        } else {
            OutcomeOfTheBall = (char) ((BallOutcome/17) + '0');
        }
    }

    void setOutcomeOfTheBall(char DesiredOutcomeOfTheBall){
        /*
            Setting pre-defined outcome for the ball
        */
        OutcomeOfTheBall = DesiredOutcomeOfTheBall;
    }

    char GetOutcomeOfTheBall() {
        /*
            Returning the outcome of the ball.
        */
        return OutcomeOfTheBall;
    }

}
