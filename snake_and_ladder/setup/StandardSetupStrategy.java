package snake_and_ladder.setup;

import snake_and_ladder.board.Board;
import snake_and_ladder.board_entity_factory.BoardEntityFactory;
import snake_and_ladder.board_entity_factory.LadderFactory;
import snake_and_ladder.board_entity_factory.SnakeFactory;

public class StandardSetupStrategy implements SetupStrategy{
    private final BoardEntityFactory snakeFactory;
    private final BoardEntityFactory ladderFactory;

    public StandardSetupStrategy() {
        this.snakeFactory = new SnakeFactory();
        this.ladderFactory = new LadderFactory();
    }

    @Override
    public void setupBoard(Board board) {

        int size = board.getSize();

        // only works for board of size 100 (i.e. 10 * 10)
        if (size != 100) {
            throw new IllegalArgumentException("Initialize a board of size 100 (i.e 10 * 10)");
        }

        // snakes
        board.addBoardEntity(snakeFactory.createEntity(98, 79));
        board.addBoardEntity(snakeFactory.createEntity(95, 75));
        board.addBoardEntity(snakeFactory.createEntity(92, 73));
        board.addBoardEntity(snakeFactory.createEntity(87, 36));
        board.addBoardEntity(snakeFactory.createEntity(62, 18));
        board.addBoardEntity(snakeFactory.createEntity(64, 60));
        board.addBoardEntity(snakeFactory.createEntity(54, 34));
        board.addBoardEntity(snakeFactory.createEntity(17, 7));

        // ladders
        board.addBoardEntity(ladderFactory.createEntity(1, 38));
        board.addBoardEntity(ladderFactory.createEntity(4, 14));
        board.addBoardEntity(ladderFactory.createEntity(9, 31));
        board.addBoardEntity(ladderFactory.createEntity(21, 42));
        board.addBoardEntity(ladderFactory.createEntity(28, 84));
        board.addBoardEntity(ladderFactory.createEntity(51, 67));
        board.addBoardEntity(ladderFactory.createEntity(72, 91));
        board.addBoardEntity(ladderFactory.createEntity(80, 99));

    }
}
