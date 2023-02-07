package player;

import input.InputInterface;
import input.PlayerNameInput;

public class Batsman extends Player {


    public Batsman() {
        InputInterface TakePlayerNameInput = new PlayerNameInput(this);
        TakePlayerNameInput.collectInput();
    }

    public void setName(String Name)
    {
        this.name = Name;
    }

}
