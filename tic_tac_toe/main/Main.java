package tic_tac_toe.main;

import tic_tac_toe.board.Board;
import tic_tac_toe.factory.RuleFactory;
import tic_tac_toe.factory.RuleType;
import tic_tac_toe.game.Game;
import tic_tac_toe.player.Player;
import tic_tac_toe.rule.Rule;

public class Main {
    public static void main(String[] args) {
        // create players
        Player player1 = new Player(1, "Larry", 'X');
        Player player2 = new Player(2, "John", 'O');

        // create rule
        Rule rule = RuleFactory.getRule(RuleType.STANDARD);

        // create game
        Game ticTacToe = new Game(3, rule);

        // create board
        Board board = ticTacToe.getBoard();

        // add players to game
        ticTacToe.addPlayer(player1);
        ticTacToe.addPlayer(player2);

        // play game
        ticTacToe.play();

    }
}
