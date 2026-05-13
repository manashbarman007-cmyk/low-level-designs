package tic_tac_toe.rule;

import tic_tac_toe.player.Player;
import tic_tac_toe.board.Board;

public interface Rule {
    boolean checkWin(Board board, Player player);
    boolean isValid(Board board, int row, int col);
}
