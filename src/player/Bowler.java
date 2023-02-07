package player;

import cricketgame.UpdatingBowlingStats;
import input.InputInterface;
import input.PlayerNameInput;

public class Bowler extends Player implements UpdatingBowlingStats {
    public void setName(String Name)
    {
        this.name = Name;
    }
    public Bowler() {
        InputInterface TakePlayerNameInput = new PlayerNameInput(this);
        TakePlayerNameInput.collectInput();
    }
}
