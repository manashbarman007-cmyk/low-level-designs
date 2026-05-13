package snake_and_ladder.rule;


import snake_and_ladder.board.Board;
import snake_and_ladder.dice.Dice;
import snake_and_ladder.player.Player;


public interface Rule {

    boolean isMoveValid (Player player, int diceRoll, Board board);

    int calcNewPos (Player player, int diceRoll, Board board);

    boolean checkWin (Player player, Board board);
}
