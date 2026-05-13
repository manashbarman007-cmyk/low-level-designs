package snake_and_ladder.setup;

import snake_and_ladder.board.Board;
import snake_and_ladder.board_entity_factory.BoardEntityFactory;
import snake_and_ladder.board_entity_factory.LadderFactory;
import snake_and_ladder.board_entity_factory.SnakeFactory;
import snake_and_ladder.pair.Pair;
import java.util.List;
import java.util.Random;

public class CustomSetupStrategy implements SetupStrategy{

    private final int numOfSnakes;
    private final int numOfLadders;
    boolean randomPosition;
    private List<Pair> snakePositions;
    private List<Pair> ladderPositions;
    private final Random random;
    private final BoardEntityFactory snakeFactory;
    private final BoardEntityFactory ladderFactory;

    public CustomSetupStrategy(int numOfSnakes, int numOfLadders,List<Pair> snakePositions, List<Pair> ladderPositions,
                               boolean randomPosition) {
        this.snakePositions = snakePositions;
        this.ladderPositions = ladderPositions;
        this.numOfSnakes = numOfSnakes;
        this.numOfLadders = numOfLadders;
        this.randomPosition = randomPosition;
        this.snakeFactory = new SnakeFactory();
        this.ladderFactory = new LadderFactory();
        this.random = new Random();
    }

    @Override
    public void setupBoard(Board board) {

        int size = board.getSize();

        // custom setup
        if (!randomPosition) {
            if (snakePositions.size() == numOfSnakes && ladderPositions.size() == numOfLadders) {
                for (Pair pair : snakePositions) {
                    if (board.canAddEntity(pair.getStart(), pair.getEnd())) {
                        board.addBoardEntity(snakeFactory.createEntity(pair.getStart(), pair.getEnd()));
                    }
                    else {
                        throw new IllegalArgumentException("Invalid Snake placement at : " + pair.getStart());
                    }
                }
                for (Pair pair : ladderPositions) {
                    if (board.canAddEntity(pair.getStart(), pair.getEnd())) {
                        board.addBoardEntity(ladderFactory.createEntity(pair.getStart(), pair.getEnd()));
                    }
                    else {
                        throw new IllegalArgumentException("Invalid Ladder placement at : " + pair.getStart());
                    }
                }
            }
            else {
                throw new IllegalArgumentException("Does not match with required constraints. Try again :)");
            }
        }
        // random setup
        else {
            int placedSnake = 0;
            int placedLadder = 0;

            int snakeMaxAttempts = numOfSnakes * 10;
            int snakeAttempts = 0;

            while (placedSnake < numOfSnakes && snakeAttempts < snakeMaxAttempts) {
                int start = getRandom(2, size - 1);
                int end = getRandom(1, start - 1); // must be smaller than start
                if (board.canAddEntity(start, end)) {
                    board.addBoardEntity(snakeFactory.createEntity(start, end));
                    placedSnake++;
                }
                snakeAttempts++;
            }
            if (placedSnake < numOfSnakes) {
                throw new RuntimeException("Could not place all snakes");
            }


            int ladderMaxAttempts = numOfLadders * 10;
            int ladderAttempts = 0;

            while (placedLadder < numOfLadders && ladderAttempts < ladderMaxAttempts) {
                int start = getRandom(1, size - 2);
                int end = getRandom(start + 1, size); // must be greater than start
                if (board.canAddEntity(start, end)) {
                    board.addBoardEntity(ladderFactory.createEntity(start, end));
                    placedLadder++;
                }
                ladderAttempts++;
            }

            if (placedLadder < numOfLadders) {
                throw new RuntimeException("Could not place all ladders");
            }
        }


    }

    private int getRandom (int min, int max) {
        return random.nextInt(min, max + 1);
    }

    public int getNumOfSnakes() {
        return numOfSnakes;
    }

    public int getNumOfLadders() {
        return numOfLadders;
    }

    public boolean isRandomPosition() {
        return randomPosition;
    }

    public void setRandomPosition(boolean randomPosition) {
        this.randomPosition = randomPosition;
    }

    public List<Pair> getSnakePositions() {
        return snakePositions;
    }

    public void setSnakePositions(List<Pair> snakePositions) {
        this.snakePositions = snakePositions;
    }

    public List<Pair> getLadderPositions() {
        return ladderPositions;
    }

    public void setLadderPositions(List<Pair> ladderPositions) {
        this.ladderPositions = ladderPositions;
    }
}
