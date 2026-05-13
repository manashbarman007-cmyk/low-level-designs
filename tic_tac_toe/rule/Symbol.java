package tic_tac_toe.rule;

public class Symbol {
    private char mark;

    public Symbol(char mark) {
        this.mark = mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return this.mark;
    }

    @Override
    public String toString() {
        return "(mark = " + mark + ")";
    }
}
