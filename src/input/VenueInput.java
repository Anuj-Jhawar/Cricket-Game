package input;

import cricketgame.CricketGame;

import java.util.Scanner;

public class VenueInput implements InputInterface{
    CricketGame game;

    public VenueInput(CricketGame Game){
        this.game = Game;
    }

    public void collectInput(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please Enter the Venue of the Game: ");
        String FormatOfTheGame = scn.nextLine();
        game.setVenueForTheGame(FormatOfTheGame);
    }
}
