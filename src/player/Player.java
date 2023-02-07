package player;

import stats.BattingStats;
import stats.BowlingStats;

import java.util.ArrayList;

public class Player {
    static int playerCount = 0;
    private ArrayList<BattingStats> battingStats = new ArrayList<BattingStats>();
    private ArrayList<BowlingStats> bowlingStats = new ArrayList<BowlingStats>();

    public Player(){
        battingStats.add(new BattingStats());
        bowlingStats.add(new BowlingStats());
    }
    public static void incrementPlayerCount(){
        playerCount++;
    }
    public static int getPlayerCount(){
        return playerCount;
    }
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void updateBattingStats(int runs) {
        battingStats.get(battingStats.size() - 1).updateBattingStats(runs);
    }

    public BattingStats getBattingStats(){
        return battingStats.get(battingStats.size() - 1);
    }

    public BowlingStats getBowlingStats(){
        return bowlingStats.get(bowlingStats.size() - 1);
    }

    public void updateBowlingStats(int outcomeOfTheBall) {
        bowlingStats.get(bowlingStats.size() - 1).updateBowlingStats(outcomeOfTheBall);
    }

}
