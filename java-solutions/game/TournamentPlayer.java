package game;

public class TournamentPlayer {
    private final Player player;
    private final String name;

    public TournamentPlayer(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }
}
