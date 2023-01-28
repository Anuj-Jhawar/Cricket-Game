import java.lang.Math;

public class Toss {
    private String TeamWhoWonThetoss;

    String CallForToss() {
        /*
            Function which decides which team have won the toss.
        */
        double value = Math.random();
        int tossValue = (int) (value * 2);
        if (tossValue == 0)
            return TeamWhoWonThetoss = "Team1";
        else
            return TeamWhoWonThetoss = "Team2";
    }
}
