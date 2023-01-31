public class PlayerFactory {
    public Player getPlayer(String type) {
        if (type.equals("Batsman")) {
            return new Batsman();
        }
        return new Bowler();
    }
}
