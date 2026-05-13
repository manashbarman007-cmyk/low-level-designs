package snake_and_ladder.game_factory;

import snake_and_ladder.board.Board;
import snake_and_ladder.dice.Dice;
import snake_and_ladder.game.Game;
import snake_and_ladder.pair.Pair;
import snake_and_ladder.rule.Rule;
import snake_and_ladder.rule.StandardRule;
import snake_and_ladder.setup.*;
import java.util.List;

public class SnakeAndLadderGameFactory {

    private final Rule rule;

    public SnakeAndLadderGameFactory() {
        this.rule = new StandardRule();
    }

    public Game standardSnakeAndLadderGame () {

        Board board = new Board(10);
        board.setupBoard(new StandardSetupStrategy());
        Dice dice = new Dice(6);

        return new Game(board, dice, rule);
    }

    public Game randomSnakeAndLadderGame (int dimension, Difficulty difficulty, int faces) {
        Board board = new Board(dimension);
        board.setupBoard(new RandomSetupStrategy(difficulty));
        Dice dice = new Dice(faces);

        return new Game(board, dice, rule);
    }

    public Game customSnakeAndLadderGame(int dimension, int faces, int numOfSnakes, int numOfLadders, List<Pair> snakePositions,
                                         List<Pair> ladderPositions, boolean randomPosition) {
        Board board = new Board(dimension);
        Dice dice = new Dice(faces);
        board.setupBoard(new CustomSetupStrategy(numOfSnakes, numOfLadders, snakePositions, ladderPositions, randomPosition));

        return new Game(board, dice, rule);
    }
}

