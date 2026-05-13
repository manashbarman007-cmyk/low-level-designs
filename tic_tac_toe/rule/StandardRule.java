package tic_tac_toe.rule;

import tic_tac_toe.player.Player;
import tic_tac_toe.board.Board;

import java.util.List;

public class StandardRule implements Rule{
    @Override
    public boolean checkWin(Board board, Player player) {

        List<List<Symbol>> grid = board.getGrid();
        int rowSize = grid.size();
        int colSize = grid.size();

        // check row win
        for (int i = 0; i < rowSize; i++) {
           if (rowCheck(i, colSize, board, player)) return true;
        }

        // check col win
        for (int i = 0; i < colSize; i++) {
            if (colCheck(i, rowSize, board, player)) return true;
        }

        // check diagonal1 win
        if (diagonal1Check(0, 0, rowSize, colSize, board, player)) return true;

        // check diagonal2 win
        if (diagonal2Check(0, colSize - 1, rowSize, board, player)) return true;

        return false;
    }

    @Override
    public boolean isValid(Board board, int row, int col) {
        int size = board.getSize();
        return row >= 0 && row < size && col >= 0 && col < size && board.isCellEmpty(row, col);
    }

    public boolean rowCheck (int row, int colSize,Board board, Player player) {
        List<List<Symbol>> grid = board.getGrid();
        char playerMark = player.getSymbol().getMark();

        for (int i = 0; i < colSize - 1; i++) {
            if (grid.get(row).get(i).getMark() == playerMark) {
                if (grid.get(row).get(i).getMark() != grid.get(row).get(i + 1).getMark()) return false;
            }
            else return false;
        }
        return true;
    }

    public boolean colCheck (int col, int rowSize,Board board, Player player) {
        List<List<Symbol>> grid = board.getGrid();
        char playerMark = player.getSymbol().getMark();

        for (int i = 0; i < rowSize - 1; i++) {
            if (grid.get(i).get(col).getMark() == playerMark) {
                if (grid.get(i).get(col).getMark() != grid.get(i + 1).get(col).getMark()) return false;
            }
            else return false;

        }
        return true;
    }

    public boolean diagonal1Check (int row, int col, int rowSize, int colSize,Board board, Player player) {

        List<List<Symbol>> grid = board.getGrid();
        char playerMark = player.getSymbol().getMark();

        while (row < rowSize - 1 && col < colSize - 1) {
            if (grid.get(row).get(col).getMark() == playerMark) {
                if (grid.get(row).get(col).getMark() != grid.get(row + 1).get(col + 1).getMark()) return false;
            }
            else return false;
            row++;
            col++;
        }
        return true;
    }

    public boolean diagonal2Check (int row, int col, int rowSize,Board board, Player player) {
        List<List<Symbol>> grid = board.getGrid();
        char playerMark = player.getSymbol().getMark();

        while (row < rowSize - 1 && col > 0) {
            if (grid.get(row).get(col).getMark() == playerMark) {
                if (grid.get(row).get(col).getMark() != grid.get(row + 1).get(col - 1).getMark()) return false;
            }
            else return false;
            row++;
            col--;
        }
        return true;
    }
}
