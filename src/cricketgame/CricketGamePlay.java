package cricketgame;

import databaseadd.AddToDataBase;
import scorecard.ScoreCard;
import storeteam.TeamMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CricketGamePlay {

    String completeToss(CricketGame game) {
        /*
            Function to complete the toss for the game.
        */
        System.out.println("Time for toss");
        int teamWhoWonTheToss = game.initiateToss();
        String Team1 = "Team1";
        if(teamWhoWonTheToss==1){
            return game.getTeam1().getTeamName();
        }
        else
            return game.getTeam2().getTeamName();
    }
    int initializeNumberOfOvers(CricketGame game) {
        /*
            Decide number of overs based on the format of the game.
        */
        String t20Format = "T20";
        String t10Format = "T10";
        int numberofOversInGame = 0;
        String Format = game.getFormat();
        if (Format.equals(t10Format)) {
            numberofOversInGame = 10;
        } else if (Format.equals(t20Format)) {
            numberofOversInGame = 20;
        } else {
            numberofOversInGame = 50;
        }
        return numberofOversInGame;
    }

    Team[] assignBattingTeam(CricketGame game, int i) {
        /*
            Assigning the batting team for the inning.
        */
        Team[] teamOrder = new Team[2];
        if (i == 0) {
            teamOrder[0] = game.getTeam1();
            teamOrder[1] = game.getTeam2();
        } else {
            teamOrder[0] = game.getTeam2();
            teamOrder[1] = game.getTeam1();
        }
        return teamOrder;
    }

    int assignNewBatsmanIfWicketFallen(int batsman1, int batsman2) {
        /*
            Returns The new Others.Batsman
        */
        return Math.max(batsman1 + 1, batsman2 + 1);
    }

    ArrayList<Integer> assignBatsmanIfOverDone(int batsman1, int batsman2, int outcomeOfTheBall) {
        /*
            Assigning the Others.Batsman depending on the OutcomeOfTheBall and If Others.Over done.
        */
        ArrayList<Integer> batsmanOrder = new ArrayList<Integer>();
        if (outcomeOfTheBall == 7) {
            int NewBatsman = assignNewBatsmanIfWicketFallen(batsman1, batsman2);
            batsmanOrder.add(batsman2);
            batsmanOrder.add(NewBatsman);
        } else {
            int RunsScoredOnTheBall = outcomeOfTheBall;
            if (RunsScoredOnTheBall % 2 == 0) {
                batsmanOrder.add(batsman2);
                batsmanOrder.add(batsman1);
            } else {
                batsmanOrder.add(batsman1);
                batsmanOrder.add(batsman2);
            }
        }
        return batsmanOrder;
    }

    ArrayList<Integer> assignBatsmanIfOverNotDone(int batsman1, int batsman2, int outcomeOfTheBall) {
        /*
            Assigning the Others.Batsman depending on the outcomeOfTheBall and If Others.Over not done.
        */
        ArrayList<Integer> BatsmanOrder = assignBatsmanIfOverDone(batsman1, batsman2, outcomeOfTheBall);
        Collections.reverse(BatsmanOrder);
        return BatsmanOrder;
    }

    ArrayList<Integer> assignBatsman(int batsman1, int batsman2, int outcomeOfTheBall, int overDone) {
        /*
            Assigning the Others.Batsman for the upcoming ball depending on the Outcome of last ball.
        */
        if (overDone == 1) {
            return assignBatsmanIfOverDone(batsman1, batsman2, outcomeOfTheBall);
        } else {
            return assignBatsmanIfOverNotDone(batsman1, batsman2, outcomeOfTheBall);
        }
    }

    int assignBowler(CricketGame game, int bowlingTeamIndex, int lastBowler) {
        /*
            Assigning Others.Bowler for the next over and making sure that bowler does not repeat.
        */
        Team currentTeam;
        if (bowlingTeamIndex == 2)
            currentTeam = game.getTeam2();
        else
            currentTeam = game.getTeam1();

        int numberOfAvailableBowlingOption = currentTeam.getNumberOfAllRounder() + currentTeam.getNumberOfBowler();
        int indexOfChosenBowler = 10 - (int) (Math.random() * (numberOfAvailableBowlingOption));
        while(lastBowler==indexOfChosenBowler){
            indexOfChosenBowler = 10 - (int) (Math.random() * (numberOfAvailableBowlingOption));
        }
        return indexOfChosenBowler;
    }
    void assignWinnerOfTheGame(CricketGame Game) {
        /*
            Function to assign Winner of the Game.
        */
        if (Game.getTeam1().getRunsScored() > Game.getTeam2().getRunsScored())
            Game.setWinner(Game.getTeam1().getTeamName());
        else
            Game.setWinner(Game.getTeam2().getTeamName());
    }

    boolean checkIfInningIsOver(CricketGame game, int wickets, int target) {
        return (target != -1 && game.getScoreOfTeam(1) > target) || wickets == 10;
    }

    void updateBattingAndBowlingStatsAfterEachBall(CricketGame game, int teamIndex, int batsmanOnStrikeIndex, int currentBowler, Ball newBall) {
        /*
            Updating the stats of batsman and bowler after every ball.
        */
        if (newBall.getOutcomeOfTheBall() == 7) {
            game.updateBattingStatsOfBatsman(teamIndex, batsmanOnStrikeIndex, 7);
        } else {
            game.updateTeamBattingStats(teamIndex, newBall.getOutcomeOfTheBall());
            game.updateBattingStatsOfBatsman(teamIndex, batsmanOnStrikeIndex, newBall.getOutcomeOfTheBall());
        }
        game.updateBowlingStatsOfBowler(teamIndex, currentBowler, newBall.getOutcomeOfTheBall());
    }

    Ball playTheBall(CricketGame game, int teamIndex, int batsmanOnStrikeIndex, int currentBowler) {
        /*
            Playing and assigning every outcome of the ball.
        */
        Ball newBall = new Ball();
        newBall.assignBallOutcome();
        game.signalOutcomeOfTheBall(newBall.getOutcomeOfTheBall());
        updateBattingAndBowlingStatsAfterEachBall(game, teamIndex, batsmanOnStrikeIndex, currentBowler, newBall);
        return newBall;
    }

    void playAInning(CricketGame game, int target, int numberOfOversInGame, int i) {
        /*
            Function to play a Inning.
        */
        int batsmanOnStrikeIndex = 0,batsman2=0,wickets=0;
        Ball lastBall = new Ball();
        lastBall.setOutcomeOfTheBall('0');
        int lastBowler = -1;
        for (int j = 0; j < numberOfOversInGame; j++) {
            int currentBowler = assignBowler(game, i, lastBowler);
            for (int k = 0; k < 6; k++) {
                int lastBallOutcome = lastBall.getOutcomeOfTheBall();
                ArrayList<Integer> batsmanOrder = assignBatsman(batsmanOnStrikeIndex, batsman2, lastBallOutcome, k == 0 ? 1 : 0);
                batsmanOnStrikeIndex = batsmanOrder.get(0);
                batsman2 = batsmanOrder.get(0);
                Ball NewBall = playTheBall(game, i, batsmanOnStrikeIndex, currentBowler);
                if (NewBall.getOutcomeOfTheBall() == 7)
                    wickets++;
                lastBall = NewBall;
                boolean checkIfInningIsOver = checkIfInningIsOver(game, wickets, target);
                if (checkIfInningIsOver)
                    break;
            }
            lastBowler = currentBowler;
            if (wickets == 10)
                break;
        }
    }

    void letsPlayTheGame(CricketGame game) {
        /*
            Function in which all the game happens.
        */
        int initializeNumberOfOvers = initializeNumberOfOvers(game);
        int target = -1;
        for (int i = 0; i < 2; i++) {
            playAInning(game, target, initializeNumberOfOvers, i==0? game.getBattingTeamIndex() : game.getBowlingTeamIndex());
            target = game.getScoreOfTeam(game.getBattingTeamIndex());
            System.out.println("Innings Break");
        }
        assignWinnerOfTheGame(game);
    }

    void printScoreCard(CricketGame game) {
        /*
            Function to print Others.ScoreCard
        */
        ScoreCard newScoreCard = new ScoreCard(game);
        newScoreCard.printScoreCard();
    }

    public void play(String tournamentName) {
        Scanner scn = new Scanner(System.in);
        CricketGame game = new CricketGame(tournamentName);
        game.setTeamsForTheGame();
        System.out.println("Game Start");
        String teamWhoWonTheToss = completeToss(game);
        System.out.println("Toss won by" + teamWhoWonTheToss);
        AddToDataBase addToDataBase = new AddToDataBase(game);
        addToDataBase.addToDataBase();
        System.out.println(teamWhoWonTheToss + " decided to bat first");
        letsPlayTheGame(game);
        System.out.println("Team Who won the match is " + game.getWinner());
        printScoreCard(game);
        TeamMap teamMap = storeteam.TeamMap.getTeamMap();
        teamMap.addTeam(game.getTeam1().getTeamName(),game.getTeam1());
        teamMap.addTeam(game.getTeam2().getTeamName(),game.getTeam2());
    }
}