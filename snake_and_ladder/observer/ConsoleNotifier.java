package snake_and_ladder.observer;

import snake_and_ladder.player.Player;

public class ConsoleNotifier implements Observer{
    private final Player player;
    public ConsoleNotifier(Player player) {
        this.player = player;
    }
    @Override
    public void update(String msg) {
        System.out.println("Message for " + player.getName() + " : " + msg);
    }
}
