public class PlayerFactory {
    public Player getPlayer(String type){
        switch (type){
            case "Batsman":
                return new Batsman();
            default:
                return new Bowler();
        }
    }
}
