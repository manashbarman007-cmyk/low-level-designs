package snake_and_ladder.board_entity_factory;

import snake_and_ladder.boardentity.BoardEntity;

public interface BoardEntityFactory {
    BoardEntity createEntity(int start, int end);
}
