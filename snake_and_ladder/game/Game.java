package snake_and_ladder.game;

import snake_and_ladder.board.Board;
import snake_and_ladder.dice.Dice;
import snake_and_ladder.observer.Observer;
import snake_and_ladder.player.Player;
import snake_and_ladder.rule.Rule;

import java.util.*;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Deque<Player> players;
    private final Rule rule;
    private final List<Observer> observers;
    private boolean isGameOver;
    private final Scanner scanner;

    public Game (Board board, Dice dice, Rule rule) {
        this.board = board;
        this.dice = dice;
        this.rule = rule;
        this.players = new ArrayDeque<>();
        this.observers = new ArrayList<>();
        this.isGameOver = false;
        this.scanner = new Scanner(System.in);
    }
    public void addPlayer (Player player) {
        this.players.offerFirst(player);
    }

    public void addObserver (Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers (String msg) {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    public String displayCurrentPosition (Player player) {
        return "The current position of " + player.getName() + " is : " + player.getPos();
    }

    public void play () {

        while (!isGameOver) {
            boolean extraTurn = false;

            if (players.isEmpty()) {
                System.out.println("Enter a player first.");
                isGameOver = true;
                continue;
            }

            Player player = players.pollFirst();

            System.out.println("Turn for " + player.getName());

            System.out.println("Press Enter to roll dice");

            scanner.nextLine();

            int diceRoll = dice.rollDice();

            notifyObservers(player.getName() + " rolled " + diceRoll);

            // for a player to enable his movement he must get the max number in the dice first
            if (!player.getCanPlayerStart()) {
                if (diceRoll == dice.getFace()) {
                    player.setCanPlayerStart(true);
                    extraTurn = true;
                }
                else {
                    notifyObservers(player.getName() + " needs " + dice.getFace() + " to start.");
                }

            } else {
                if (diceRoll == dice.getFace()) {
                    // if it is a valid move
                    if (rule.isMoveValid(player, diceRoll, board)) {

                        player.setMaxCounter(player.getMaxCounter() + 1);

                        if (player.getMaxCounter() == 3) {
                            player.setMaxCounter(0);
                            player.setPos(1);
                            extraTurn = false;
                        }else {
                            player.setPos(rule.calcNewPos(player, diceRoll, board));
                            extraTurn = true;
                        }

                    }
                    else { // not a valid move
                        player.setMaxCounter(0);
                        notifyObservers("Not a valid move for " + player.getName());
                    }

                }
                else { // if (diceRoll != dice.getFace())

                    // if it is a valid move
                    if (rule.isMoveValid(player, diceRoll, board)) {
                        player.setMaxCounter(0);
                        player.setPos(rule.calcNewPos(player, diceRoll, board));
                    }
                    else {
                        player.setMaxCounter(0);
                        notifyObservers("Not a valid move for " + player.getName());
                    }
                }
                // notify
                notifyObservers(displayCurrentPosition(player));

                // win condition
                if (rule.checkWin(player, board)) {
                    player.setScore(player.getScore() + 1);
                    notifyObservers("Congratulation!!! " + player.getName() + ". Your score is : " + player.getScore());
                    isGameOver = true;
                    continue;
                }
            }
            if (extraTurn) {
                // add to front so that player gets an extra turn
                players.offerFirst(player);
            }else {
                // add to last
                players.offerLast(player);
            }
        }
    }
}
