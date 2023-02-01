public class Ball {
    private final String BatsmanName;
    private final String BowlerName;
    private final String FielderName;
    private final boolean IsANoBall;
    private char OutcomeOfTheBall;

    Ball() {
        OutcomeOfTheBall = 'N';
        BatsmanName = "Not defined";
        BowlerName = "Not defined";
        FielderName = "Not defined";
        IsANoBall = false;
    }

    void assignBallOutcome() {
        /*
            Assigning the outcome of the ball.
        */
        int BallOutcome = (int) (Math.random() * 100);
        if (BallOutcome >= 95) {
            OutcomeOfTheBall = 'W';
        } else {
            OutcomeOfTheBall = (char) ((BallOutcome / 17) + '0');
        }
    }

    void setOutcomeOfTheBall(char DesiredOutcomeOfTheBall) {
        /*
            Setting pre-defined outcome for the ball
        */
        OutcomeOfTheBall = DesiredOutcomeOfTheBall;
    }

    char getOutcomeOfTheBall() {
        /*
            Returning the outcome of the ball.
        */
        return OutcomeOfTheBall;
    }

}
