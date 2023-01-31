import java.lang.Math;

public class Toss {
    private String TeamWhoWonTheToss;

    String CallForToss() {
        /*
            Function which decides which team have won the toss.
        */
        double value = Math.random();
        int tossValue = (int) (value * 2);
        if (tossValue == 0)
            return TeamWhoWonTheToss = "Team1";
        else
            return TeamWhoWonTheToss = "Team2";
    }
    String getTeamWhoWonTheToss(){
        return TeamWhoWonTheToss;
    }
}
