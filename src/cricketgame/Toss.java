package cricketgame;

public class Toss {
    private String teamWhoWonTheToss;

    String callForToss() {
        /*
            Function which decides which team have won the toss.
        */
        double value = Math.random();
        int tossValue = (int) (value * 2);
        if (tossValue == 0)
            return teamWhoWonTheToss = "Team1";
        else
            return teamWhoWonTheToss = "Team2";
    }

    String getTeamWhoWonTheToss() {
        return teamWhoWonTheToss;
    }
}
