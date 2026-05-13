package snake_and_ladder.main;


import snake_and_ladder.game.Game;
import snake_and_ladder.get_game.GetGame;
import snake_and_ladder.observer.ConsoleNotifier;
import snake_and_ladder.observer.Observer;
import snake_and_ladder.player.Player;

public class Main {
    public static void main(String[] args) {
        GetGame getGame = new GetGame();
        Game game = getGame.getGame();
        Player p1 = new Player(1, "Larry");
        Player p2 = new Player(2, "Bob");
        Observer o1 = new ConsoleNotifier(p1);
        Observer o2 = new ConsoleNotifier(p2);
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addObserver(o1);
        game.addObserver(o2);
        game.play();
    }
}
