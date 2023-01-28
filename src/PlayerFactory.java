public class PlayerFactory {
    public Player getPlayer(String type){
        switch (type){
            case "Batsman":
                return new Batsman();
            case "Bowler":
                return new Bowler();
            default:
                return new AllRounder();
        }
    }
}
