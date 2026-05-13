package snake_and_ladder.board;

import snake_and_ladder.boardentity.BoardEntity;
import snake_and_ladder.setup.SetupStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int size;
    private final List<BoardEntity> entities;
    private final Map<Integer, BoardEntity> map;

    public Board(int dimension) {
        this.size = dimension * dimension;
        this.entities = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public boolean canAddEntity (int start, int end) {
        return start > 0 && end > 0 && start < size && end <= size && start != end && !map.containsKey(start);
    }

    public void addBoardEntity (BoardEntity entity) {
        if (canAddEntity(entity.getStart(), entity.getEnd())) {
            entities.add(entity);
            map.put(entity.getStart(), entity);
        }
        else throw new IllegalArgumentException("Invalid Placement");
    }

    public void setupBoard (SetupStrategy strategy) {
        strategy.setupBoard(this);
        this.displayBoard();
    }

    public int getSize() {
        return size;
    }

    public List<BoardEntity> getEntities() {
        return entities;
    }

    public Map<Integer, BoardEntity> getMap() {
        return map;
    }

    public void displayBoard() {
        int snakeCount = 0;
        int ladderCount = 0;

        System.out.println("Board Configuration");
        System.out.println("Board size : " + size);

        for (BoardEntity entity : entities) {
            String entityName = entity.name();
            if (entityName.equalsIgnoreCase("Ladder")) {
                ladderCount++;
            }
            else {
                snakeCount++;
            }
        }

        System.out.println("Snake count : " + snakeCount);

        for (BoardEntity entity : entities) {
            String entityName = entity.name();
            if (entityName.equalsIgnoreCase("Snake")) {
                entity.display();
            }
        }

        System.out.println("Ladder count : " + ladderCount);
        for (BoardEntity entity : entities) {
            String entityName = entity.name();
            if (entityName.equalsIgnoreCase("Ladder")) {
                entity.display();
            }
        }

    }
}
