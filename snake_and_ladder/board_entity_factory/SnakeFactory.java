package snake_and_ladder.board_entity_factory;

import snake_and_ladder.boardentity.BoardEntity;
import snake_and_ladder.boardentity.Snake;

public class SnakeFactory implements BoardEntityFactory{
    @Override
    public BoardEntity createEntity(int start, int end) {
        return new Snake(start, end);
    }
}
