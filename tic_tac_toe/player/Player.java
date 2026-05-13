package tic_tac_toe.player;

import tic_tac_toe.observer.ConsoleNotifier;
import tic_tac_toe.observer.Observer;
import tic_tac_toe.rule.Symbol;

public class Player {
    private final Observer observer;
    private final int id;
    private final String name;
    private final Symbol symbol;
    private int score;

    public Player (int id, String name, char ch) {
        this.symbol = new Symbol(ch);
        this.observer = new ConsoleNotifier(this);
        this.id = id;
        this.name = name;
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Observer getObserver() {
        return observer;
    }
}
