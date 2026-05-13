package tic_tac_toe.board;

import tic_tac_toe.player.Player;
import tic_tac_toe.rule.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Symbol>> grid;
    private final int size;

    public Board (int size) { // composition
        this.size = size;
        this.grid = new ArrayList<>();
        for (int i = 0 ; i < size; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                grid.get(i).add(new Symbol('_'));
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {

        return grid.get(row).get(col).getMark() == '_';
    }

    public char getCell (int row, int col) {

        return grid.get(row).get(col).getMark();
    }

    public void markCell (int row, int col, Player player) {
        char playerMark = player.getSymbol().getMark();
        grid.get(row).get(col).setMark(playerMark);
    }

    public int getSize() {
        return this.size;
    }

    public List<List<Symbol>> getGrid() {
        return this.grid;
    }

    public void displayBoard() {
        for (List<Symbol> list : grid) {
            System.out.println(list);
        }
    }

}
