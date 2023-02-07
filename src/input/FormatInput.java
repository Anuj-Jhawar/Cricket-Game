package input;

import cricketgame.CricketGame;

import java.util.Scanner;

public class FormatInput implements InputInterface{
    CricketGame game;

    public FormatInput(CricketGame game){
        this.game = game;
    }

    public void collectInput(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please Enter the format of the Game from the following:");
        System.out.println("T10, T20, ODI");
        String FormatOfTheGame = scn.nextLine();
        game.setFormatForTheGame(FormatOfTheGame);
    }

}
