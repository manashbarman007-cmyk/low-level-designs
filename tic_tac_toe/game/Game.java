package tic_tac_toe.game;

import tic_tac_toe.player.Player;
import tic_tac_toe.board.Board;
import tic_tac_toe.observer.Observer;
import tic_tac_toe.rule.Rule;

import java.util.*;

public class Game {
    private int count;
    private final Board board;
    private final Rule rule;
    private boolean gameOver;
    Deque<Player> players;
    List<Observer> observers;

    public Game(int size, Rule rule) {
        this.count = 0;
        this.gameOver = false;
        this.board = new Board(size);
        this.observers = new ArrayList<>();
        this.rule = rule;
        this.players = new ArrayDeque<>();
    }

    public void addObserver (Observer observer) {
        observers.add(observer);
    }
    public void removeObserver (Observer observer) {
        observers.remove(observer);
    }
    public void notifyAllObservers(String msg) {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    public void addPlayer (Player player) {
        addObserver(player.getObserver());
        players.offerLast(player);
    }

    public void removePlayer (Player player) {
        removeObserver(player.getObserver());
        players.remove(player);
    }

    public void play () {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            if (players.size() < 2) {
                System.out.println("At least 2 players are needed");
                return;
            }
            Player player = players.pollFirst();
            if (player == null) {
                throw new IllegalArgumentException("No player");
            }
            char playerMark = player.getSymbol().getMark();
            System.out.println(player.getName() + " enter row and col : ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (rule.isValid(board, row, col)) {
                notifyAllObservers(player.getName() + " has put " + playerMark + " at " + "(" + row + ", " + col + ")");
                board.markCell(row, col, player);
                board.displayBoard();
                count += 1;
            }else {
                System.out.println("Enter valid move.");
                players.offerFirst(player);
                continue;
            }

            // check win / draw
            if (rule.checkWin(board, player)) {
                player.setScore(player.getScore() + 1);
                notifyAllObservers(player.getName() + " is the winner. Score = " + player.getScore());
                gameOver = true;
            }
            else if (count == board.getSize() * board.getSize()) {
                notifyAllObservers("It is a draw");
                gameOver = true;
            }

            players.offerLast(player);
        }

        scanner.close();
    }

    public Board getBoard () {
        return this.board;
    }
}
