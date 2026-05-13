package snake_and_ladder.setup;

import snake_and_ladder.board.Board;
import snake_and_ladder.board_entity_factory.BoardEntityFactory;
import snake_and_ladder.board_entity_factory.LadderFactory;
import snake_and_ladder.board_entity_factory.SnakeFactory;

import java.util.Random;

public class RandomSetupStrategy implements SetupStrategy{
    private final Difficulty difficulty;
    private final Random random;
    private final BoardEntityFactory snakeFactory;
    private final BoardEntityFactory ladderFactory;

    public RandomSetupStrategy(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.random = new Random();
        this.snakeFactory = new SnakeFactory();
        this.ladderFactory = new LadderFactory();
    }

    @Override
    public void setupBoard(Board board) {

        switch (difficulty) {
            case EASY -> setUpWithDifficulty(board, 0.3); // 30% snakes, 70% ladder
            case MEDIUM -> setUpWithDifficulty(board, 0.5); // 50% snakes, 50% ladder
            case HARD -> setUpWithDifficulty(board, 0.7); // 70% snakes, 30% ladder
        }

    }

    private void setUpWithDifficulty ( Board board, double snakeProbability) {

        int size = board.getSize();
        int totalEntities = size / 16; // i.e. roughly 16% of board has entities
        int placed = 0;
        int maxAttempts = totalEntities * 10;
        int attempts = 0;

        while (placed < totalEntities && attempts < maxAttempts) {
            boolean isSnake = Math.random() < snakeProbability;
            int start, end;
            if (isSnake) { // for snake
                start = getRandom(2, size - 1);
                end = getRandom(1, start - 1); // end should be lower than start
            }else {  // for ladder
                start = getRandom(1, size - 2);
                end = getRandom(start + 1, size); // end should be higher than start
            }
            if (board.canAddEntity(start, end)) {
                // add snake
                if (isSnake) {
                    board.addBoardEntity(snakeFactory.createEntity(start, end));
                }
                // add ladder
                else {
                    board.addBoardEntity(ladderFactory.createEntity(start, end));
                }
                placed++;
            }
            attempts++;
        }

    }

    private int getRandom (int min, int max) {
        return random.nextInt(min, max + 1);
    }
}
