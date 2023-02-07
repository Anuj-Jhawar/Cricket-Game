package player;

import stats.BattingStats;
import stats.BowlingStats;
import stats.Stats;

import java.util.ArrayList;

public class Player {
    static int playerCount = 0;
    private ArrayList<Stats> battingStats = new ArrayList<Stats>();
    private ArrayList<Stats> bowlingStats = new ArrayList<Stats>();

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
        battingStats.get(battingStats.size() - 1).updateStats(runs);
    }

    public BattingStats getBattingStats(){
        return (BattingStats) battingStats.get(battingStats.size() - 1);
    }

    public BowlingStats getBowlingStats(){
        return (BowlingStats) bowlingStats.get(bowlingStats.size() - 1);
    }

    public void updateBowlingStats(int outcomeOfTheBall) {
        bowlingStats.get(bowlingStats.size() - 1).updateStats(outcomeOfTheBall);
    }

}
